package message;

public class CreateEntityMessage extends Message{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int numPlayer;
	private String typeEntity;
	private String nameEntity;
	private float posX;
	private float posY;
	private float rotation;
	
	public CreateEntityMessage (int numPlayer, String typeEntity, String nameEntity, float posX, float posY, float rotation) {
		this.numPlayer = numPlayer;
		this.typeEntity = typeEntity;
		this.nameEntity = nameEntity;
		this.posX = posX;
		this.posY = posY;
	}
	
	public int getNumPlayer() {
		return numPlayer;
	}
	
	public String getTypeEntity() {
		return typeEntity;
	}
	
	public String getNameEntity() {
		return nameEntity;
	}
	
	
	public float getPosX() {
		return posX;
	}
	
	public float getPosY() {
		return posY;
	}
	
	public float getRotation() {
		return rotation;
	}
	
}
