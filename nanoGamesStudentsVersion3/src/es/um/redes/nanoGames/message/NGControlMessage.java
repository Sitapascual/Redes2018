package es.um.redes.nanoGames.message;

public class NGControlMessage extends NGMessage{
	protected byte opcode;


	public byte getOpcode() {
		return opcode;
	}


	@Override
	protected byte[] toByteArray() {
		// TODO Apéndice de método generado automáticamente
		return null;
	}
	
}
