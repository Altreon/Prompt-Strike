package message;

public class PosMessage extends Message{

	private static final long serialVersionUID = 1L; //needed to be serializable
	
	private int numPlayer;
	private String nameUnit;
	private float posX;
	private float posY;
	
	public PosMessage (int numPlayer, String nameUnit, float posX, float posY) {
		this.numPlayer = numPlayer;
		this.nameUnit = nameUnit;
		this.posX = posX;
		this.posY = posY;
	}
	
	public int getNumPlayer() {
		return numPlayer;
	}
	
	public String getNameUnit() {
		return nameUnit;
	}
	
	public float getPosX() {
		return posX;
	}
	
	public float getPosY() {
		return posY;
	}
	
}
