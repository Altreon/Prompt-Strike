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
		while(!isConnected()) {try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}};
		UDPConnexion.setPort(TCPConnexion.getSocket().getLocalPort());
		UDPThread = new Thread(UDPConnexion);
		UDPThread.start();
	}

	public void sendCommand(String command) {
		TCPConnexion.sendCommand(command);
	}

	public boolean isConnected() {
		return TCPConnexion != null && TCPConnexion.getSocket() != null;
	}
}
