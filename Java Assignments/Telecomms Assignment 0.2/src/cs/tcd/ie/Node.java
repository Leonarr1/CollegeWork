package cs.tcd.ie;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.concurrent.CountDownLatch;

/**
 * The class is the basis for the sensor, gateway and server nodes.
 * 
 *
 */
public abstract class Node {
	static final int PACKETSIZE = 65536;

	static DatagramSocket socket;
	Listener listener;
	CountDownLatch latch;
	int timeoutErrorCode=0;
	
	Node() {
		latch= new CountDownLatch(1);
		listener= new Listener();
		listener.setDaemon(true);
		listener.start();
	}
	
	
	public abstract void onReceipt(DatagramPacket packet) throws IOException, InterruptedException, Exception;
	
	public abstract void timeout() throws IOException, InterruptedException;
	
	/**
	 *
	 * Listener thread
	 * 
	 * Listens for incoming packets on a datagram socket and informs registered receivers about incoming packets.
	 */
	class Listener extends Thread {
		
		/*
		 *  Telling the listener that the socket has been initialized 
		 */
		public void go() {
			latch.countDown();
		}
		
		/*
		 * Listen for incoming packets and inform receivers
		 */
		public void run() {
			try {
				latch.await();
				// Endless loop: attempt to receive packet, notify receivers, etc
				while(true) {
					DatagramPacket packet = new DatagramPacket(new byte[PACKETSIZE], PACKETSIZE);
					try
					{
						socket.receive(packet);

						onReceipt(packet);
						timeoutErrorCode=0;
					}
					catch (SocketTimeoutException ste)
					{
						timeoutErrorCode++;
						timeout();
						
					}
				}
			} catch (Exception e) {if (!(e instanceof SocketException)) e.printStackTrace();}
		}
	}
}
