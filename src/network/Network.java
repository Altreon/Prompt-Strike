package network;

public class Network {
	private TCPConnexion TCPConnexion;
	private UDPConnexion UDPConnexion;
	
	private Thread TCPThread;
	private Thread UDPThread;
	
	public Network () {
		
	}
	
	public void connect () {
		TCPConnexion = new TCPConnexion();
		TCPThread = new Thread(TCPConnexion);
		TCPThread.start();
		
		UDPConnexion = new UDPConnexion();
		UDPThread = new Thread(UDPConnexion);
		UDPThread.start();
	}

	public void sendCommand(String command) {
		TCPConnexion.sendCommand(command);		
	}

	public boolean isConnected() {
		return TCPConnexion != null;
	}
}
