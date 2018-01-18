package network;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client implements Runnable{
	private Socket socket              = null;
	   private DataInputStream  console   = null;
	   private DataOutputStream streamOut = null;

	@Override
	public void run() {
			System.out.println("Establishing connection...");
		     try {  socket = new Socket("serverName", 0);
		         System.out.println("Connected: " + socket);
		         start();
		     } catch(UnknownHostException uhe) {  
		    	 System.out.println("Host unknown: " + uhe.getMessage());
		      }
		      catch(IOException ioe) {  
		    	  System.out.println("Unexpected exception: " + ioe.getMessage());
		      }
		      String line = "";
		      while (!line.equals(".bye")) {  
		    	 try {  line = console.readUTF();
		            streamOut.writeUTF(line);
		            streamOut.flush();
		         } catch(IOException ioe) {  
		        	 System.out.println("Sending error: " + ioe.getMessage());
		         }
		      }
	}
	
		public void start() throws IOException
		   {  console   = new DataInputStream(System.in);
		      streamOut = new DataOutputStream(socket.getOutputStream());
		   }
		   public void stop()
		   {  try
		      {  if (console   != null)  console.close();
		         if (streamOut != null)  streamOut.close();
		         if (socket    != null)  socket.close();
		      }
		      catch(IOException ioe)
		      {  System.out.println("Error closing ...");
		      }
		   }
}
