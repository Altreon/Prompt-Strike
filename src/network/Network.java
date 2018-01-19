package network;

public class Network {
	private Connexion connexion;
	
	private Thread networkThread;
	
	public Network () {
		
	}
	
	public void connect () {
		connexion = new Connexion();
		networkThread = new Thread(connexion);
		networkThread.start();
	}

	public void sendCommand(String command) {
		connexion.sendCommand(command);		
	}

	public boolean isConnected() {
		return true;
	}
}
