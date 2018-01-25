package network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import main.Command;

public class TCPConnexion implements Runnable{
	private Socket socket;
	private DataInputStream streamIn;
	private DataOutputStream streamOut;

	@Override
	public void run() {
		System.out.println("Establishing connection...");
		try {  
			socket = new Socket("127.0.0.1", 10);
		    System.out.println("Connected: " + socket);
		    start();
		} catch(UnknownHostException uhe) {
			System.out.println("Host unknown: " + uhe.getMessage());
		} catch(IOException ioe) {  
			System.out.println("Unexpected exception: " + ioe.getMessage());
		}
		if(socket != null) {
			boolean connect = true;
			while (connect) {  
				try {  
					int numPlayer = streamIn.readInt();
					String line = streamIn.readUTF();
					boolean correct = streamIn.readBoolean();
			        Command.processServerCommand(numPlayer, line, correct);
			    } catch(IOException ioe) {  
			    	System.out.println("error: " + ioe.getMessage());
			    	stop();
			    	connect = false;
			    }
			}
		}
	}
	
	public void start() throws IOException{  
		streamIn   = new DataInputStream(socket.getInputStream());
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
    
	public void receiveData() {
		
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
