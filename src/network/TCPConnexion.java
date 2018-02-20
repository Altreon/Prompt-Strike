package network;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.Socket;
import java.net.UnknownHostException;

import main.Command;
import main.Game;
import message.Message;

public class TCPConnexion implements Runnable{
	private Socket socket;
	private ObjectInputStream streamIn;
	private DataOutputStream streamOut;

	@Override
	public void run() {
		try {  
		    start();
		} catch(IOException ioe) {  
			System.out.println("Unexpected exception: " + ioe.getMessage());
		}
		
		if(socket != null) {
			boolean connect = true;
			while (connect) {
				try {
					receiveMessage();
				} catch (IOException e) {
					connect = false;
					//e.printStackTrace();
					System.out.println("connexion lost");
					disconnect();
				}
				//Command.processServerCommand(numPlayer, line, correct);
			}
		}
	}
	
	public boolean connect(String IPAddress) {
		System.out.println("Establishing connection at " + IPAddress + "...");
		try {
			socket = new Socket(IPAddress, 10);
		} catch (UnknownHostException e) {
			//e.printStackTrace();
			System.out.println("incorrect IP address");
			return false;
		} catch (IOException e) {
			//e.printStackTrace();
			System.out.println("connexion has failed!");
			return false;
		}
	    System.out.println("Connected: " + socket);
	    return true;		
	}
	
	public void disconnect () {
		stop();
		Game.disconnect();
	}
	
	public void start() throws IOException{  
		streamIn   = new ObjectInputStream(socket.getInputStream());
	    streamOut = new DataOutputStream(socket.getOutputStream());
	}
	
	public void stop() {  
    	try {
    		streamIn.close();
	    	streamOut.close();
	        socket.close();
    	}
      catch(IOException ioe) {
    	  System.out.println("Error closing ...");
        }
    }
	
	public Socket getSocket() {
		return socket;
	}
    
	public void receiveMessage() throws IOException {
		try {
			Message message = (Message) streamIn.readObject();
					
			Game.processMessage(message);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
    public void sendCommand (String command) {
    	try {
			streamOut.writeUTF(command);
			streamOut.flush();
		} catch (IOException ioe) {
			System.out.println("Error sending command:" + ioe.getMessage());
		}
    }
}
