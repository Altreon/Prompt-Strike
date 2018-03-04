package message;

public class RotMessage extends Message{

	private static final long serialVersionUID = 1L; //needed to be serializable
	
	private int numPlayer;
	private String nameUnit;
	private float rotation;
	private int idPart;
	
	public RotMessage (int numPlayer, String nameUnit, float rotation, int idPart) {
		this.numPlayer = numPlayer;
		this.nameUnit = nameUnit;
		this.rotation = rotation;
		this.idPart = idPart;
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
	
	public int getIdPart() {
		return idPart;
	}	
}
