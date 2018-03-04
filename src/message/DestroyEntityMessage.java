package message;

public class DestroyEntityMessage extends Message{

	private static final long serialVersionUID = 1L; //needed to be serializable
	
	private int numPlayer;
	private String typeEntity;
	private String nameEntity;
	
	public DestroyEntityMessage (int numPlayer, String typeEntity, String nameEntity) {
		this.numPlayer = numPlayer;
		this.typeEntity = typeEntity;
		this.nameEntity = nameEntity;
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
	
}
