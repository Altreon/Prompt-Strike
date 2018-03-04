package network;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import game.Game;
import message.Message;

public class UDPConnexion implements Runnable{
	
	public int port;
	
	private DatagramSocket socket;
	
	public boolean connect;

	@Override
	public void run() {
		start();
		
		if(socket != null) {
			connect = true;
			while (connect) {  
				try {  
					byte[] buffer = new byte[256]; // 256 more than enough (conventional) to store any message for this application
	                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
	                
	                socket.receive(packet);
	                
	                receiveMessage(packet);
			    } catch(IOException ioe) {  
			    	System.out.println("UDP error: " + ioe.getMessage());
			    	stop();
			    }
			}
		}
	}
	
	public void setPort(int port) {
		this.port = port;
	}
	
	public void start() {
		try {
			socket = new DatagramSocket(port);
		} catch (SocketException se) {
			System.out.println("Error creating UDP-Connexion");
			se.printStackTrace();
		}
	}
	
	public void stop(){
		connect = false;
    	socket.close();
    }
    
	public void receiveMessage(DatagramPacket packet) {
		try {
			ObjectInputStream iStream = new ObjectInputStream(new ByteArrayInputStream(packet.getData()));
			Message message = (Message) iStream.readObject();
			iStream.close();
					
			Game.processMessage(message);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
