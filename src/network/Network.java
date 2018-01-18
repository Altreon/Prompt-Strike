package network;

public class Network {
	
	private Server server;
	private Client client;
	
	private Thread networkThread;
	
	public Network () {
		
	}
	
	public void createServer () {
		server = new Server();
		networkThread = new Thread(server);
		networkThread.start();
	}
	
	public void createClient () {
		client = new Client();
		networkThread = new Thread(server);
		networkThread.start();
	}

}
