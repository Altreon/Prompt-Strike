package message;

public class CommandMessage extends Message{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String command;
	private boolean correct;
	
	public CommandMessage (String command, boolean correct) {
		this.command = command;
		this.correct = correct;
	}
	
	public String getCommand() {
		return command;
	}
	
	
	public boolean getCorrect() {
		return correct;
	}	
}
