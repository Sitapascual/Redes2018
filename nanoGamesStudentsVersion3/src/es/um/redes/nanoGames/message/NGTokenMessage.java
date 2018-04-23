package es.um.redes.nanoGames.message;

import java.io.DataInputStream;
import java.nio.ByteBuffer;

public class NGTokenMessage extends NGMessage{
	protected byte opcode; //1byte
	private long token; //8 byte
	private byte campo1;
	private int campo2;
	public NGTokenMessage(byte opcode, long token){
		this.opcode = opcode;
		this.token = token;
	}

	@Override
	public byte[] toByteArray() {		
		ByteBuffer bb = ByteBuffer.allocate(5);
		bb.put(campo1);
		bb.putInt(campo2);
		byte[] men = bb.array();
		
		return men;
	}
	
	public byte[] readFromIS(DataInputStream dis){
		try {
			campo1 = dis.readByte();
			campo2 = dis.readInt();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return null;
	}
	
	public byte[] readFromString(DataInputStream dis){
		try {
			campo1 = dis.readByte();
			campo2 = dis.readInt();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return null;
	}

	public byte getOpcode() {
		return opcode;
	}

	public long getToken() {
		return token;
	}
	
	
	
	
}
