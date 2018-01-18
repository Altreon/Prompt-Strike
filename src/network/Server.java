package network;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {
	
	private Socket socket;
	private ServerSocket server;
	private DataInputStream streamIn;

	@Override
	public void run() {
		try {
			System.out.println("server initializing...");
	        server = new ServerSocket(0);  
	        System.out.println("Server started: " + server);
	        System.out.println("Waiting for a other player..."); 
	        socket = server.accept();
	        System.out.println("Player found: " + socket);
	        open();
	        boolean done = false;
	        while (!done)
	        {  try
	           {  String line = streamIn.readUTF();
	              System.out.println(line);
	              done = line.equals(".bye");
	           }
	           catch(IOException ioe)
	           {  done = true;
	           }
	        }
	        close();
		}catch(IOException ioe) {
			System.out.println(ioe); 
		}
		
	}
	
	public void open() throws IOException
	   {  streamIn = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
	   }
	
	public void close() throws IOException {
		if (socket != null) {
			socket.close();
		}
	    if (streamIn != null) {
	    	streamIn.close();
	    }
	}
	
}
