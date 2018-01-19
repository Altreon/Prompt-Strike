package network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Connexion implements Runnable{
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
			String line = "";
			while (!line.equals("disconnect")) {  
				try {  
					line = streamIn.readUTF();
			        streamOut.writeUTF(line);
			        streamOut.flush();
			    } catch(IOException ioe) {  
			    	System.out.println("error: " + ioe.getMessage());
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
