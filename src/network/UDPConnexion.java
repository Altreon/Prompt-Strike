package network;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.MulticastSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import main.Command;
import main.Game;
import message.Message;
import message.PosMessage;

public class UDPConnexion implements Runnable{
	
	public static int port = 2345;
	
	//private DatagramSocket socket;
	private MulticastSocket socket;

	@Override
	public void run() {
		try {  
			socket = new MulticastSocket(port);
		} catch(IOException ioe) {  
			System.out.println("Unexpected exception: " + ioe.getMessage());
		}
		if(socket != null) {
			boolean connect = true;
			while (connect) {  
				try {  
					byte[] buffer = new byte[8192];
	                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
	                
	                socket.receive(packet);
	                
	                receiveMessage(packet);
					
					//String command = new String(packet.getData());
					//System.out.println(command);
			    } catch(IOException ioe) {  
			    	System.out.println("Uerror: " + ioe.getMessage());
			    	stop();
			    	connect = false;
			    }
			}
		}
	}
	
	public void setPort(int port) {
		this.port = port;
	}
	
	public int getPort() {
		return port;
	}
	
	public void stop(){  
    	socket.close();
    }
    
	public void receiveMessage(DatagramPacket packet) {
		try {
			ObjectInputStream iStream = new ObjectInputStream(new ByteArrayInputStream(packet.getData()));
			Message message = (Message) iStream.readObject();
			iStream.close();
					
			Game.processMessage(message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
