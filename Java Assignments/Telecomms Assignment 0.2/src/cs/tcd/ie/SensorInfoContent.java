package cs.tcd.ie;

import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.nio.ByteBuffer;

/**
*
* SensorInfoContent class
* 
* this class is an extension of packetContent an includes methods
*  for extracting data from the packet
*
*/
public class SensorInfoContent extends PacketContent {
	
	
	 						
	byte[] id;				// Tells gateway where packet came from		
	byte[] destination;		// Destination server address
	byte[] source;			// Sensor source address
	byte[] seqNr;			// Sequence Number
	byte[] type;			// type of packet SENSORPACKET or ACKPACKET
	byte[] errorFlag;		// Carries error information
	
	String sensorInfo;
	

	/**
	 * Constructor 
	 * 
	 * Takes in a DatagramPacket extracts the data to create SensorInfoContent.
	 * 
	 * @param DatagramPacket packet to Extract data from.
	 * @return void
	 */
	SensorInfoContent(DatagramPacket packet) 
	{
		byte[] payload= null;
		byte[] buffer= null;
		buffer= packet.getData();
		payload= new byte[packet.getLength()-HEADER_LENGTH];
		id = new byte[PACKET_ID_LENGTH];
		destination = new byte[DST_ADDRESS_LENGTH];
		source = new byte[SRC_ADDRESS_LENGTH];
		seqNr = new byte[SEQ_NUMBER_LENGTH];
		type = new byte[TYPE_LENGTH];
		errorFlag = new byte[ERROR_FLAG_LENGTH];

		//Extract payload
		System.arraycopy(buffer, HEADER_LENGTH, payload, 0, packet.getLength()-HEADER_LENGTH);
		//Extract packet id
		System.arraycopy(buffer, 0, id, 0, PACKET_ID_LENGTH);
		//Extract destination address
		System.arraycopy(buffer, PACKET_ID_LENGTH, destination, 0, DST_ADDRESS_LENGTH);
		//Extract source address
		System.arraycopy(buffer, PACKET_ID_LENGTH+DST_ADDRESS_LENGTH, source, 0, SRC_ADDRESS_LENGTH);
		//Extract sequence number
		System.arraycopy(buffer, (PACKET_ID_LENGTH+DST_ADDRESS_LENGTH+SRC_ADDRESS_LENGTH), seqNr, 0, SEQ_NUMBER_LENGTH);
		//Extract type
		System.arraycopy(buffer, (PACKET_ID_LENGTH+DST_ADDRESS_LENGTH+SRC_ADDRESS_LENGTH+
				SEQ_NUMBER_LENGTH), type, 0, TYPE_LENGTH);
		//Extract error flag
		System.arraycopy(buffer, (PACKET_ID_LENGTH+DST_ADDRESS_LENGTH+SRC_ADDRESS_LENGTH+
				SEQ_NUMBER_LENGTH+TYPE_LENGTH), errorFlag, 0, ERROR_FLAG_LENGTH);

		sensorInfo = new String(payload);
	}
	

	/**
	 * Writes the content into an ObjectOutputStream
	 *
	 */
	protected void toObjectOutputStream(ObjectOutputStream oout) {
		try {
			oout.writeUTF(sensorInfo);
		}
		catch(Exception e) {e.printStackTrace();}
	}
	
	
	/**
	 * Writes the content into an ObjectOutputStream
	 *
	 */
	public DatagramPacket toDatagramPacket() {
		DatagramPacket packet= null;
		byte[] buffer= null;
		byte[] payload= null;
		byte[] header= null;

		try {
			payload= sensorInfo.getBytes();
			header= new byte[HEADER_LENGTH];
			buffer= new byte[header.length+payload.length];
			
			
			//Encloses given payload in the buffer with the other relevant info
			System.arraycopy(id, 0, buffer, 0, id.length);
			System.arraycopy(destination, 0, buffer, id.length, destination.length);
			System.arraycopy(source, 0, buffer, (id.length+destination.length), source.length);
			System.arraycopy(seqNr, 0, buffer, (id.length+destination.length+source.length), PacketContent.SEQ_NUMBER_LENGTH);
			System.arraycopy(type, 0, buffer, (id.length+destination.length+source.length+seqNr.length), PacketContent.TYPE_LENGTH);
			System.arraycopy(errorFlag, 0, buffer, (type.length+id.length+destination.length+source.length+seqNr.length), PacketContent.ERROR_FLAG_LENGTH);
			System.arraycopy(payload, 0, buffer, PacketContent.HEADER_LENGTH, payload.length);
			packet= new DatagramPacket(buffer, buffer.length);
		}
		catch(Exception e) {e.printStackTrace();}
		return packet;
	}

	
	/**
	 * Returns the content of the packet as String.
	 * 
	 * @return Returns the content of the packet as String.
	 */
	public String toString() {
		return sensorInfo;
	}
	
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
	 * Returns the sequence of the packet as Int.
	 * 
	 * @return Returns the sequence of the packet as Int.
	 */
	public int getSequnceNumber(){
		return ByteBuffer.wrap(this.seqNr).getInt();
	}
	
	/**
	 * Sets the sequence of the packet to nxtSeqNr.
	 * @param nxtSeqNr to put into the packet
	 */
	public void setSequenceNumber(int nxtSeqNr) {
		this.seqNr =  ByteBuffer.allocate(4).putInt(nxtSeqNr).array();
	}
	
	/**
	 * Returns the type of the packet as Int.
	 * 
	 * @return Returns the type of the packet as Int.
	 */
	public int getType(){
		return ByteBuffer.wrap(this.type).getInt();
	}
	
	/**
	 * Sets the type of the packet to ACKPACKET.
	 * 
	 */
	public void setTypeACK() {
		 this.type =  ByteBuffer.allocate(4).putInt(ACKPACKET).array();
	}
	
	/**
	 * Sets the type of the packet to SENSORPACKET.
	 * 
	 */
	public void setTypeSensor() {
		 this.type =  ByteBuffer.allocate(4).putInt(SENSORPACKET).array();
	}

	/**
	 * Returns the id of the packet as Int.
	 * 
	 * @return Returns the type of the packet as Int.
	 */
	public int getId() {
		return ByteBuffer.wrap(this.id).getInt();
	}
	
	/**
	 * Sets the id of the packet to SENSORPACKET.
	 * @param id number of the id to be set to 
	 */
	public void setId(int id) {
		this.id = ByteBuffer.allocate(4).putInt(id).array();
	}

	/**
	 * Returns the ErrorFlag of the packet as Int.
	 * 
	 * @return Returns the type of the packet as Int.
	 */
	public int getErrorFlag() {
		return ByteBuffer.wrap(this.errorFlag).getInt();
	}
	
	/**
	 * Returns true if the errorFlag is set.
	 * 
	 * @return Boolean true if the errorFlag is set.
	 */
	public boolean isErrorSet() {
		return ByteBuffer.wrap(this.errorFlag).getInt()==1;
	}

	/**
	 * Sets ErrorFlag to input byte[].
	 * @param errorFlag value to set error flag to
	 */
	public void setErrorFlag(byte[] errorFlag) {
		this.errorFlag = errorFlag;
	}
	
	/**
	 * Sets ErrorFlag to on. value of 1.
	 * 
	 */
	public void setErrorFlagOn() {
		this.errorFlag = ByteBuffer.allocate(4).putInt(1).array();;
	}
	
	/**
	 * Sets ErrorFlag to off. value of 0.
	 * 
	 */
	public void setErrorFlagOff() {
		this.errorFlag = ByteBuffer.allocate(4).putInt(0).array();;
	}


	
	

}
