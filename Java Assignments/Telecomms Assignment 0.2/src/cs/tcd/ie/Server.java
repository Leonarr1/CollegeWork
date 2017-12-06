package cs.tcd.ie;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

import java.util.HashMap;

import tcdIO.Terminal;

/**
*
* Server class
* 
* an instance receivers packets, sends ACK packets and keeps track of clients and sequence numbers;
* will send ACK or NAK to client depending on received packet
*
*/

public class Server extends Node {
	static final int DEFAULT_PORT = 50001;
	HashMap<Integer, Integer> hmap = new HashMap<Integer, Integer>();
	int nxtSeqNr;

	Terminal terminal;
	
	/**
	 * Constructor
	 * 
	 * Attempts to create socket at given port
	 * 
	 * @param Terminal terminal 
	 * @param int serverPort
	 * 
	 * 
	 */
	Server(Terminal terminal, int port) {
		try {
			this.terminal= terminal;
			socket= new DatagramSocket(port);
			this.nxtSeqNr = 0;
			listener.go();
		}
		catch(java.lang.Exception e) {e.printStackTrace();}
	}

	/**
	 * Assume that incoming packets contain a sensorInfo.
	 * 
	 * will check a hashmap for packets for an ID and match the sequence number from the packet
	 * with the last sequence number it got from a packet with the same ID.
	 * 
	 * Caches ID's from new clients in a hashmap to track different sequence numbers
	 * 
	 * @param packet packet being analyised
	 */
	public void onReceipt(DatagramPacket packet) {
		try {
			terminal.println("Received packet");
			
			SensorInfoContent recievedPacket= new SensorInfoContent(packet);
			String recievedSequenceNumber = Integer.toString(recievedPacket.getSequnceNumber());
			terminal.println("Sensor ID/port: " + recievedPacket.getId() + "\n");
			terminal.println("Sequence number of packet = " + recievedSequenceNumber);
			
			if(hmap.containsKey(recievedPacket.getId()))
			{
				nxtSeqNr = hmap.get(recievedPacket.getId());
			}
			else
			{
				hmap.put(recievedPacket.getId(), recievedPacket.getSequnceNumber());
				nxtSeqNr = recievedPacket.getSequnceNumber();
			}
			
			
			//Verify if sequence number is expected sequence number
			if(recievedPacket.getSequnceNumber() == this.getNxtSeqNr()){

				terminal.println("Correct expected sequence number.\n");
				terminal.println("Contents of packet = '" + recievedPacket.toString() + "'\n");
				
				this.updateNxtSeqNr();
				hmap.put(recievedPacket.getId(), nxtSeqNr);
				
				SensorInfoContent ack = recievedPacket;
				//Indicate ACK
				ack.setTypeACK();
				ack.setErrorFlagOff();
				DatagramPacket responsePacket = ack.toDatagramPacket();
				responsePacket.setSocketAddress(packet.getSocketAddress());
				
				terminal.println("Sending acknowledgement response\nto gateway ('ACK')...");
				socket.send(responsePacket);
			}
			else
			{
				terminal.println("Incorrect sequence number.");
				terminal.println("Contents of packet = '" + recievedPacket.toString() + "'\n");
				terminal.println("Expected sequence number '" + this.getNxtSeqNr() + "'.");
				terminal.println("Recieved sequence number '" + recievedPacket.getSequnceNumber() + "'.");
				
				SensorInfoContent nak = recievedPacket;;
				//Indicate NAK
				nak.setTypeACK();
				nak.setErrorFlagOn();
				//Sends back the expected sequence number in the sequence number section of the header
				nak.setSequenceNumber(this.getNxtSeqNr());
				DatagramPacket responsePacket = nak.toDatagramPacket();
				responsePacket.setSocketAddress(packet.getSocketAddress());
				
				terminal.println("Sending negative acknowledgement response\nto gateway ('NAK')...");
				socket.send(responsePacket);
			}
			
		}
		catch(Exception e) {e.printStackTrace();}
	}
	

	/**
	 * Returns the expected sequence number of the server as Int.
	 * 
	 * @return Returns the nxtSeqNr of the server as Int.
	 */
	public int getNxtSeqNr() {
		return nxtSeqNr;
	}
	/**
	 * Sets the expected sequence of the server to 0.
	 * 
	 */
	public void resetNxtSeqNr(){
		nxtSeqNr=0;
	}
	/**
	 * Updates the expected sequence of the server.
	 * Increments it by one.
	 * 
	 */
	public void updateNxtSeqNr(){
		nxtSeqNr++;
	}
	
	/**
	 * Sets the sequence of the packet to nxtSeqNr.
	 * @param nxtSeqNr nxtSeqNr to put into the packet
	 */
	public void setNxtSeqNr(int nxtSeqNr) {
		this.nxtSeqNr = nxtSeqNr;
	}
	
	
	/**
	 * Initializes the server to wait for packets.
	 * @throws Exception
	 */
	public synchronized void start() throws Exception {
		socket.setSoTimeout(5000);
		terminal.println("Waiting for contact");
		this.wait();
	}
	
	/** 
	 * Main method 
	 * 
	 * Starts the server
	 * 
	 */
	public static void main(String[] args) {
		try {					
			Terminal terminal= new Terminal("Server");
			(new Server(terminal, DEFAULT_PORT)).start();
			terminal.println("Program completed");
		} catch(java.lang.Exception e) {e.printStackTrace();}
	}

	/**
	 * timeout method
	 * 
	 * is called by the node abstract class when a timeout exception occurs
	 * will alert the user how long it's been waiting for a packet
	 * 
	 */
	public void timeout() 
	{
		// prints message every 30 seconds
		if(timeoutErrorCode%12 ==0)
		{
			terminal.println("Waiting on packet for: " + timeoutErrorCode*5 + " Seconds");
		}
		
	}
}
