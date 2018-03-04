package message;

public class FireMessage extends Message{

	private static final long serialVersionUID = 1L; //needed to be serializable
	
	private int numPlayer;
	private String nameUnit;
	private float ImpactPosX;
	private float ImpactPosY;
	
	public FireMessage (int numPlayer, String nameUnit, float ImpactPosX, float ImpactPosY) {
		this.numPlayer = numPlayer;
		this.nameUnit = nameUnit;
		this.ImpactPosX = ImpactPosX;
		this.ImpactPosY = ImpactPosY;
	}
	
	public int getNumPlayer() {
		return numPlayer;
	}
	
	public String getNameUnit() {
		return nameUnit;
	}
	
	
	public float getImpactPosX() {
		return ImpactPosX;
	}
	
	public float getImpactPosY() {
		return ImpactPosY;
	}
	
}