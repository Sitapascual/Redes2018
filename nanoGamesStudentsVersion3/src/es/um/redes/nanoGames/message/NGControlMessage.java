package es.um.redes.nanoGames.message;

public class NGControlMessage extends NGMessage{
	protected byte opcode;


	public byte getOpcode() {
		return opcode;
	}


	@Override
	protected byte[] toByteArray() {
		// TODO Ap�ndice de m�todo generado autom�ticamente
		return null;
	}
	
}
