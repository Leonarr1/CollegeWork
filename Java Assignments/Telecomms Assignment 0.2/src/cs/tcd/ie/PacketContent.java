package cs.tcd.ie;

import java.net.DatagramPacket;
import java.nio.ByteBuffer;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;

/**
 * The class is the basis for packet contents of various types.
 * 
 *
 */
public abstract class PacketContent {

	public static final int ACKPACKET= 10;
	public static final int FILEINFO= 100;
	public static final int SENSORPACKET= 200;
	
	public static final byte HEADER_LENGTH = 28;
	public static final byte PACKET_ID_LENGTH = 4;
	public static final byte DST_ADDRESS_LENGTH = 6;
	public static final byte SRC_ADDRESS_LENGTH = 6;
	public static final byte SEQ_NUMBER_LENGTH = 4;
	public static final byte TYPE_LENGTH = 4;
	public static final byte ERROR_FLAG_LENGTH = 4;

	static byte[] type;		// type of packet 
	byte[] destination;		// Destination server address
	byte[] source;			// Sensor source address
	byte[] id;
	

	/**
	 * Constructs an object out of a datagram packet.
	 * @param packet Packet to analyse.
	 */
	public static PacketContent fromDatagramPacket(DatagramPacket packet) {
		PacketContent content= null;
		try 
		{	
			// Extract type from packet
			content= new SensorInfoContent(packet);
		}
		catch(Exception e) {e.printStackTrace();}

		return content;
	}

	
	/**
	 * This method is used to transform content into an output stream.
	 * 
	 * @param out Stream to write the content for the packet to.
	 */
	protected abstract void toObjectOutputStream(ObjectOutputStream out);
	
	/**
	 * Returns the content of the object as DatagramPacket.
	 * 
	 * @return Returns the content of the object as DatagramPacket.
	 */
	public DatagramPacket toDatagramPacket() {
		DatagramPacket packet= null;

		try {
			ByteArrayOutputStream bout;
			ObjectOutputStream oout;
			byte[] data;

			bout= new ByteArrayOutputStream();
			oout= new ObjectOutputStream(bout);

			//oout.writeInt(type);         // write type to stream
			toObjectOutputStream(oout);  // write content to stream depending on type

			oout.flush();
			data= bout.toByteArray(); // convert content to byte array

			packet= new DatagramPacket(data, data.length); // create packet from byte array
			oout.close();
			bout.close();
		}
		catch(Exception e) {e.printStackTrace();}

		return packet;
	}


	/**
	 * Returns the content of the packet as String.
	 * @return Returns the content of the packet as String.
	 */
	public abstract String toString();
	
	/**
	 * Returns the destination of the packet as Int.
	 * 
	 * @return Returns the destination of the packet as String.
	 */
	public int getDestination(){
		return ByteBuffer.wrap(this.destination).getInt();
	}
	
	/**
	 * Returns the source of the packet as Int.
	 * 
	 * @return Returns the source of the packet as Int.
	 */
	public int getSource(){
		return ByteBuffer.wrap(this.source).getInt();
	}
	
	/**
	 * Returns the type of the packet.
	 * 
	 * @return Returns the type of the packet.
	 */
	public int getType() {
		return ByteBuffer.wrap(type).getInt();
	}
	
	
	
}
