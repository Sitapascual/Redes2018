package es.um.redes.nanoGames.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.BrokenBarrierException;

import es.um.redes.nanoGames.broker.BrokerClient;
import es.um.redes.nanoGames.server.roomManager.NGRoomManager;

/**
 * A new thread runs for each connected client
 */
public class NGServerThread extends Thread {
	
	//Possible states of the connected client
	private static final byte PRE_TOKEN = 1;
	private static final byte PRE_REGISTRATION = 2;
	private static final byte OFF_ROOM = 3;
	private static final byte IN_ROOM = 4;
	
	//Time difference between the token provided by the client and the one obtained from the broker directly
	private static final long TOKEN_THRESHOLD = 1500; //15 seconds
	//Socket to exchange messages with the client
	private Socket socket = null;
	//Global and shared manager between the threads
	private NGServerManager serverManager = null;
	//Input and Output Streams
	private DataInputStream dis;
	private DataOutputStream dos;
	//Utility class to communicate with the Broker
	BrokerClient brokerClient;
	//Current player
	NGPlayerInfo player;
	//Current RoomManager (it depends on the room the user enters)
	NGRoomManager roomManager;
	//TODO Add additional fields

	public NGServerThread(NGServerManager manager, Socket socket, String brokerHostname) {
		//Initialization of the thread
		this.serverManager = manager;
		this.socket = socket;
		this.brokerClient = new BrokerClient(brokerHostname);
		//TODO
	}

	//Main loop
	public void run() {
		try {
			//We obtain the streams from the socket
			dis = new DataInputStream(socket.getInputStream());
			dos = new DataOutputStream(socket.getOutputStream());
			//The first step is to receive and to verify the token
			receiveAndVerifyToken();
			//The second step is to receive and to verify the nick name
			receiveAndVerifyNickname();
			//While the connection is alive...
			while (true) {
				//TODO Rest of messages according to the automata
			}
		} catch (Exception e) {
			//If an error occurs with the communications the user is removed from all the managers and the connection is closed
			//TODO
		}
		//TODO Close the socket
	}

	//Receive and verify Token
	//TODO
	private void receiveAndVerifyToken() throws IOException {
		boolean tokenVerified = false;
		while (!tokenVerified) {
				//We extract the token from the message
				DataInputStream dis = new DataInputStream(socket.getInputStream());
				long tokenMensaje = dis.readLong();
				//now we obtain a new token from the broker
				long tokenServidor = this.brokerClient.getToken();
				//We check the token and send an answer to the client
				if((tokenServidor - tokenMensaje) < TOKEN_THRESHOLD){
					tokenVerified = true;
					System.out.println("Verificacion correcta");
				}
		}
	}

	//We obtain the nick and we request the server manager to verify if it is duplicated
	//TODO
	private void receiveAndVerifyNickname() throws IOException {
		boolean nickVerified = false;
		//this loop runs until the nick provided is not duplicated
		while (!nickVerified) {
				//We obtain the nick from the message
				//we try to add the player in the server manager
				//if success we send to the client the NICK_OK message
				//otherwise we send DUPLICATED_NICK
		}
	}

	//We send to the client the room list
	//TODO
	private void sendRoomList() throws IOException {
		//The room list is obtained from the server manager
		//Then we build all the required data to send the message to the client 
	}

	//Method to process messages received when the player is in the room
	//TODO
	private void processRoomMessages() throws IOException {
		//First we send the rules and the initial status
		//Now we check for incoming messages, status updates and new challenges
		boolean exit = false;
		while (!exit) {
			//TODO
		}
	}

}
