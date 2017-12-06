/**
 * 
 */
package cs.tcd.ie;

import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.io.IOException;

import tcdIO.*;

/**
 *
 * Client class
 * 
 * an instance accepts user input, sends packets and receivers ACK packets.
 * Also handles timeout errors and sequence NAK's
 *
 */
public class Sensor extends Node {
	static final int DEFAULT_SRC_PORT = 50000;
	static final int DEFAULT_DST_PORT = 50001;
	static final int DEFAULT_GATEWAY_PORT = 40000;
	static final String DEFAULT_DST_NODE = "localhost";	
	byte[] seqNr;
	byte[] errorFlag;
	//type = 200 for sensor packets and 10 for ACK packets
	byte[] type;
	int sensorPortNumber;
	int sensorID;
	String payload;
	int dst;
	DatagramPacket previousPacket;
	Terminal terminal;
	InetSocketAddress gatewayAddress;
	
	/**
	 * Constructor
	 * 
	 * Attempts to create socket at given port and create an InetSocketAddress for the destinations
	 * 
	 * @param Terminal terminal 
	 * @param String gatewayHost
	 * @param int gatewayPort
	 * 
	 * 
	 */
	Sensor(Terminal terminal, String gatewayHost,  int gatewayPort) {
		try {
			this.terminal= terminal;
			this.sensorPortNumber = terminal.readInt("Sensor port number: ");
			//Creates address for gateway
			gatewayAddress = new InetSocketAddress(gatewayHost, gatewayPort);
			
			//Creates socket at srcPort
			try {
			socket = new DatagramSocket(sensorPortNumber);
			}
			catch(java.lang.Exception e) 
			{
				terminal.println("ERROR: Port already in use");
				(new Sensor(terminal, DEFAULT_DST_NODE, DEFAULT_GATEWAY_PORT)).start();
			}
			this.sensorID = sensorPortNumber;
			payload = terminal.readString("Sensor info to send: "); 
			
			dst = terminal.readInt("Destination address: ");
			
			this.seqNr = ByteBuffer.allocate(PacketContent.SEQ_NUMBER_LENGTH).putInt(0).array();
			this.type = ByteBuffer.allocate(PacketContent.TYPE_LENGTH).putInt(PacketContent.SENSORPACKET).array();
			this.errorFlag = ByteBuffer.allocate(PacketContent.ERROR_FLAG_LENGTH).putInt(0).array();
			previousPacket = null;
			listener.go();
			
		}
		catch(java.lang.Exception e) {e.printStackTrace();}
	}

	
	
	/**
	 * Receive method
	 * 
	 * @param packet the packet received
	 * 
	 * Assume that incoming packets contain a sensorInfo and 
	 * determine what to do depending on the errorFlag.
	 * @throws Exception 
	 */
	public synchronized void onReceipt(DatagramPacket packet) throws Exception {
		SensorInfoContent content= new SensorInfoContent(packet);
		terminal.println("Respone recieved from gateway...");
		//If ACK
		if(!content.isErrorSet())
		{
			this.carryOn(packet);
		}
		//If NAK re-send packet
		else{ 
			this.resendPacket(packet);
		}
		this.notify();
	}
	
	/**
	 * Next message method
	 * 
	 * gets new input from user and sends it back to the server
	 * 
	 * @param packet the recieved packet 
	 * 
	 * @throws IOException
	 * @throws InterruptedException 
	 */
	public synchronized void carryOn(DatagramPacket packet) throws IOException, InterruptedException{
		byte[] newPayload = null;
		byte[] newBuffer = null;
		byte[] type = new byte[PacketContent.TYPE_LENGTH];
		byte[] oldBuffer = null;
		byte[] headerData = new byte[PacketContent.HEADER_LENGTH];
		
		String testMessage = terminal.readString("New info to send: ");
		//newPayload = (terminal.readString("New info to send: ")).getBytes();
		newPayload = testMessage.getBytes();
		oldBuffer = packet.getData();
		//Reset type
		type = ByteBuffer.allocate(4).putInt(PacketContent.SENSORPACKET).array();
		
		//Update sequence number
		int sequenceNumber = ByteBuffer.wrap(this.seqNr).getInt();
		sequenceNumber++;
		this.seqNr = ByteBuffer.allocate(4).putInt(sequenceNumber).array();
		
		//Test when sensor sends the wrong sequence number
		if(testMessage.equals("test"))
		{
			int testSequence = terminal.readInt("Input packet sequence for test: ");
			this.seqNr = ByteBuffer.allocate(4).putInt(testSequence).array();
		}
		
		//Creates a buffer to contain the information
		newBuffer= new byte[PacketContent.HEADER_LENGTH + newPayload.length];
		//Transfer old header data from old buffer
		System.arraycopy(oldBuffer, 0, headerData, 0, PacketContent.HEADER_LENGTH);
		System.arraycopy(headerData, 0, newBuffer, 0 , PacketContent.HEADER_LENGTH);
		//Transfer in new payload
		System.arraycopy(newPayload, 0, newBuffer, PacketContent.HEADER_LENGTH, newPayload.length);
		
		//Tranfer in updated type
		System.arraycopy(type, 0, newBuffer, 
				(PacketContent.HEADER_LENGTH-PacketContent.ERROR_FLAG_LENGTH-PacketContent.TYPE_LENGTH),
				PacketContent.TYPE_LENGTH);
		
		//Transfer in new sequence number
		System.arraycopy(this.seqNr, 0, newBuffer, (PacketContent.PACKET_ID_LENGTH+PacketContent.DST_ADDRESS_LENGTH + PacketContent.SRC_ADDRESS_LENGTH), PacketContent.SEQ_NUMBER_LENGTH);
		
		terminal.println("\nSending new packet to gateway...");
		packet= new DatagramPacket(newBuffer, newBuffer.length, gatewayAddress);
		this.previousPacket = packet;
		socket.send(packet);
		terminal.println("New packet sent to gateway\n");
	}
	
	
	
	/**
	 * resendPacket Method - Allows system to re-send packet if NAK is received
	 *
	 * @param packet the wrong packet the sever sent back
	 * 
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	public synchronized void resendPacket(DatagramPacket packet) throws IOException, InterruptedException
	{
		SensorInfoContent  wrongContent = new SensorInfoContent(packet);
		SensorInfoContent previousContent = new SensorInfoContent(previousPacket);
		
		if(wrongContent.getSequnceNumber()==previousContent.getSequnceNumber())
		{
			socket.send(previousPacket);
		}
		else
		{
			byte[] newPayload = null;
			byte[] newBuffer = null;
			byte[] type = new byte[PacketContent.TYPE_LENGTH];
			byte[] oldBuffer = null;
			byte[] headerData = new byte[PacketContent.HEADER_LENGTH];
			
			terminal.println("Expected Sequence: " + wrongContent.getSequnceNumber());
			int CorrectSeqNr = terminal.readInt("Input correct sequence number: ");
			this.seqNr = ByteBuffer.allocate(4).putInt(CorrectSeqNr).array();
			
			newPayload = (terminal.readString("Info to re-send: ")).getBytes();
			oldBuffer = packet.getData();
			//Reset type
			type = ByteBuffer.allocate(4).putInt(PacketContent.SENSORPACKET).array();
			
			this.seqNr = ByteBuffer.allocate(4).putInt(CorrectSeqNr).array();
			
			//Creates a buffer to contain the information
			newBuffer= new byte[PacketContent.HEADER_LENGTH + newPayload.length];
			//Transfer old header data from old buffer
			System.arraycopy(oldBuffer, 0, headerData, 0, PacketContent.HEADER_LENGTH);
			System.arraycopy(headerData, 0, newBuffer, 0 , PacketContent.HEADER_LENGTH);
			//Transfer in new payload
			System.arraycopy(newPayload, 0, newBuffer, PacketContent.HEADER_LENGTH, newPayload.length);
			
			//Tranfer in updated flag
			System.arraycopy(type, 0, newBuffer, 
					(PacketContent.HEADER_LENGTH-PacketContent.ERROR_FLAG_LENGTH-PacketContent.TYPE_LENGTH),
					PacketContent.TYPE_LENGTH);
			
			//Transfer in new sequence number
			System.arraycopy(this.seqNr, 0, newBuffer, (PacketContent.PACKET_ID_LENGTH+PacketContent.DST_ADDRESS_LENGTH + PacketContent.SRC_ADDRESS_LENGTH), PacketContent.SEQ_NUMBER_LENGTH);
			
			terminal.println("\nRe-sending new packet to gateway...");
			packet= new DatagramPacket(newBuffer, newBuffer.length, gatewayAddress);
			this.previousPacket = packet;
			socket.send(packet);
			terminal.println("Packet re-sent to gateway\n");
		}
	}

	
	/**
	 * Sender Method
	 * 
	 * will create and send the first packet from the sensor 
	 * also initializes the timeout length
	 * 
	 * @throws Exception 
	 */
	public synchronized void start() throws Exception 
	{
		
		socket.setSoTimeout(5000);
		DatagramPacket packet= null;

		byte[] payload = null;
		byte[] id = null;
		byte[] dstAddress = null;
		byte[] srcAddress = null;
		byte[] seqNr = null;
		byte[] type = null;
		byte[] errorFlag = null;
		byte[] buffer= null;
		
		dstAddress = new byte[PacketContent.DST_ADDRESS_LENGTH];
		srcAddress = new byte[PacketContent.SRC_ADDRESS_LENGTH];
		seqNr = this.seqNr;
		type = this.type;
		errorFlag = this.errorFlag;
	
		payload = this.payload.getBytes();
		id = ByteBuffer.allocate(PacketContent.PACKET_ID_LENGTH).putInt(sensorID).array();
		//int dst = terminal.readInt("Destination address: ");
		dstAddress = ByteBuffer.allocate(PacketContent.DST_ADDRESS_LENGTH).putInt(this.dst).array();
		
		srcAddress = ByteBuffer.allocate(PacketContent.SRC_ADDRESS_LENGTH).putInt(sensorPortNumber).array();
			
		//Creates a buffer to contain the information
		buffer= new byte[PacketContent.HEADER_LENGTH + payload.length];
		
		System.arraycopy(id, 0, buffer, 0, id.length);
		System.arraycopy(dstAddress, 0, buffer, id.length, dstAddress.length);
		System.arraycopy(srcAddress, 0, buffer, (id.length+dstAddress.length), srcAddress.length);
		System.arraycopy(seqNr, 0, buffer, (id.length+dstAddress.length+srcAddress.length), PacketContent.SEQ_NUMBER_LENGTH);
		System.arraycopy(type, 0, buffer, (id.length+dstAddress.length+srcAddress.length+seqNr.length), PacketContent.TYPE_LENGTH);
		System.arraycopy(errorFlag, 0, buffer, (type.length+id.length+dstAddress.length+srcAddress.length+seqNr.length), PacketContent.ERROR_FLAG_LENGTH);
		System.arraycopy(payload, 0, buffer, PacketContent.HEADER_LENGTH, payload.length);
			
		
		terminal.println("\nSending packet to gateway...");
		packet= new DatagramPacket(buffer, buffer.length, gatewayAddress);
		this.previousPacket = packet;		
		socket.send(packet);
		terminal.println("Packet sent to gateway\n");
		//terminal.println("Packet: \n" + Arrays.toString(buffer)); // for packet example pic in report
		this.wait();

		
	}
	
	/**
	 * Main method
	 * 
	 * starts the sensor 
	 */
	public static void main(String[] args) {
		try {					
			Terminal terminal= new Terminal("Sensor");
			
			(new Sensor(terminal, DEFAULT_DST_NODE, DEFAULT_GATEWAY_PORT)).start();
			
			
			terminal.println("Program completed");
		} catch(java.lang.Exception e) {e.printStackTrace();}
	}



	/**
	 * timeout method
	 * 
	 * is called by the node abstract class when a timeout exception occurs
	 * will attempt to resend the packet and will ask to reconnect or terminate if it fails
	 * 
	 */
	public void timeout() 
	{
		terminal.println("No response from server: " + timeoutErrorCode*5 + " Seconds");

		if(timeoutErrorCode > 3)
		{
			terminal.println("Connection timed out after: " + timeoutErrorCode*5 + " Seconds");
			terminal.println("Connection terminated");
			socket.close();
			String opt = terminal.readString("Restablish connection(y/n): ");
			while(!opt.toLowerCase().equals("y")||!opt.toLowerCase().equals("n"))
			{
				if(opt.equals("y"))
				{
					try 
					{
						(new Sensor(terminal, DEFAULT_DST_NODE, DEFAULT_GATEWAY_PORT)).start();
					}
					catch (Exception e) {e.printStackTrace();}

				}
				else
					System.exit(1);

			}
		}
		else
		{
			try 
			{
				terminal.println("attempting to resend...\n");
				socket.send(previousPacket);
			} 
			catch (IOException e) {e.printStackTrace();}
		}
	}

}
