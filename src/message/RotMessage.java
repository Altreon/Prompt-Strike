package message;

public class RotMessage extends Message{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int numPlayer;
	private String nameUnit;
	private float rotation;
	
	public RotMessage (int numPlayer, String nameUnit, float rotation) {
		this.numPlayer = numPlayer;
		this.nameUnit = nameUnit;
		this.rotation = rotation;
	}
	
	public int getNumPlayer() {
		return numPlayer;
	}
	
	public String getNameUnit() {
		return nameUnit;
	}
	
	
	public float getRotation() {
		return rotation;
	}	
}
