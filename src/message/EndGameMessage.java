package message;

public class EndGameMessage extends Message{

	private static final long serialVersionUID = 1L; //needed to be serializable
	
	private boolean win;
	
	public EndGameMessage (boolean win) {
		this.win = win;
	}
	
	public boolean areYouWin() {
		return win;
	}
	
}
