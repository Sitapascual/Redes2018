package es.um.redes.nanoGames.client.comm;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

import com.sun.corba.se.impl.protocol.giopmsgheaders.Message;

import es.um.redes.nanoGames.message.NGControlMessage;
import es.um.redes.nanoGames.message.NGMessage;
import es.um.redes.nanoGames.message.NGTokenMessage;

//This class provides the functionality required to exchange messages between the client and the game server 
public class NGGameClient {
	private Socket socket;
	protected DataOutputStream dos;
	protected DataInputStream dis;
	
	private static final int SERVER_PORT = 6969;

	public NGGameClient(String serverName){
		//Creation of the socket and streams
		this.socket = new Socket();
		try {
			socket.bind(new InetSocketAddress(serverName, SERVER_PORT));
		} catch (Exception e) {
			// TODO: handle exception
		}
		this.dos = new DataOutputStream(dos);
		this.dis = new DataInputStream(dis);
		
	}

	public boolean verifyToken(long token) throws IOException {
		//SND(token) and RCV(TOKEN_VALID) or RCV(TOKEN_INVALID)
		DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
		dos.writeLong(token);
		DataInputStream dis = new DataInputStream(socket.getInputStream());
		boolean verificar = dis.readBoolean();
		if(!verificar){
			return false;
		}
		
		//Make message (NGMessage.makeXXMessage)
		NGTokenMessage message = (NGTokenMessage) NGMessage.makeTokenMessage(NGMessage.OP_SEND_TOKEN, token);
		byte[] rawMessage = message.toByteArray();
		//Send messge (dos.write())
		dos.write(rawMessage);
		//Receive response (NGMessage.readMessageFromSocket)
		NGControlMessage response = (NGControlMessage) NGMessage.readMessageFromSocket(dis);
		//Devuelvo true si el token es valido
		return (response.getOpcode() == NGMessage.OP_TOKEN_VALID);
		
		
	}
	/*
	public boolean registerNickname(String nick) throws IOException {
		//SND(nick) and RCV(NICK_OK) or RCV(NICK_DUPLICATED)
		//TODO
	}*/

	//TODO
	//add additional methods for all the messages to be exchanged between client and game server
	
	
	//Used by the shell in order to check if there is data available to read 
	public boolean isDataAvailable() throws IOException {
		return (dis.available() != 0);
	}
	

	//To close the communication with the server
	public void disconnect() {
		//TODO
	}
}
