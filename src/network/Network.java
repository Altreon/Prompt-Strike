package network;

public class Network {
	
	private TCPConnexion TCPConnexion;
	private UDPConnexion UDPConnexion;
	
	private Thread TCPThread;
	private Thread UDPThread;
	
	public boolean connect (String iPAddress) {
		if(tryConnect(iPAddress)) {
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
			
			return true;
		}else {
			return false;
		}
	}

	public void sendCommand(String command) {
		TCPConnexion.sendCommand(command);
	}
	
	private boolean tryConnect(String iPAddress) {
		TCPConnexion = new TCPConnexion();
		return TCPConnexion.connect(iPAddress);
	}
	
	public void disconnect() {
		TCPConnexion.stop();
		TCPConnexion = null;
		UDPConnexion.stop();
		UDPConnexion = null;
	}

	public boolean isConnected() {
		return TCPConnexion != null && TCPConnexion.getSocket() != null;
	}
}
