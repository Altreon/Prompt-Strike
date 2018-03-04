package message;

public class NewPlayerMessage extends Message{

	private static final long serialVersionUID = 1L; //needed to be serializable
	
	private String namePlayer;
	
	public NewPlayerMessage (String namePlayer) {
		this.namePlayer = namePlayer;
	}
	
	public String getnamePlayer() {
		return namePlayer;
	}
	
}
