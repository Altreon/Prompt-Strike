package map;


public class Ressource extends Tile{
	
	public int amount;

	public Ressource(String type, int posX, int posY) {
		super(type);
		setPos(224 + 64*posX, 64*posY);
	}

}
