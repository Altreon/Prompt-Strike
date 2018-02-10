package message;

public class UpdateMoneyMessage extends Message{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int numPlayer;
	private int money;
	
	public UpdateMoneyMessage (int numPlayer, int money) {
		this.numPlayer = numPlayer;
		this.money = money;
	}
	
	public int getNumPlayer() {
		return numPlayer;
	}
	
	public int getMoney() {
		return money;
	}
	
}