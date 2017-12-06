package cs.tcd.ie;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;


import tcdIO.Terminal;


/**
 *Gateway class
 *
 *Receives packets checks which direction they should be going 
 *and sends them on to their final destination
 * 
 *
 */
public class Gateway extends Node {
	
	static final int DEFAULT_PORT = 40000;
	//static final int DEFAULT_DST_PORT = 50001;
	static final String DEFAULT_DST_NODE = "localhost";	
	
	Terminal terminal;
	InetSocketAddress dstAddress;
	InetSocketAddress srcAddress;
	
	/**
	 * Constructor
	 * 
	 * Attempts to create socket at given port
	 * 
	 * @param terminal 
	 * @param port gateway port number
	 * 
	 * 
	 */
	Gateway(Terminal terminal, int port) {
		try {
			this.terminal= terminal;
			socket= new DatagramSocket(port);
			listener.go();
		}
		catch(java.lang.Exception e) {e.printStackTrace();}
	}

	/**
	 * Assume that incoming packets contain a sensorInfo.
	 * 
	 * will check the packets type to see which direction it should go and sends
	 * it on to it's specified final destination
	 * 
	 * @param packet to be analyised
	 */
	public void onReceipt(DatagramPacket packet) 
	{
		try {
			terminal.println("Received packet");

			SensorInfoContent content= new SensorInfoContent(packet);

			switch(content.getType())
			{
			case PacketContent.SENSORPACKET:
			{
				terminal.println("\nFrom sensor");
				terminal.println("Sending to server...");
				packet.setPort(content.getDestination());
				socket.send(packet);
				terminal.println("\nPacket sent to server");
				break;
			}
			case PacketContent.ACKPACKET:
			{
				terminal.println("\nFrom server");
				terminal.println("Sending to sensor...");
				packet.setPort(content.getSource());
				socket.send(packet);
				terminal.println("\nPacket sent to sensor");
				
				break;
			}
			}
		}
		catch(Exception e) {e.printStackTrace();} 

	}
	/**
	 * Initializes the gateway to wait for packets.
	 * Initializes the timeout timer length
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
	 * starts the gateway 
	 */
	public static void main(String[] args) {
		try {					
			Terminal terminal= new Terminal("Gateway");		
			(new Gateway(terminal, DEFAULT_PORT )).start();
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
		if(timeoutErrorCode%6 ==0)
		{
			terminal.println("Waiting on packet for: " + timeoutErrorCode*5 + " Seconds");
		}
		
	}

}
