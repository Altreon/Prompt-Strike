package map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Tile {
	private String type;
	private Sprite sprite;
	
	public Tile (String type, int posX, int posY) {
		this.type = type;
		sprite = new Sprite(new Texture("Tiles/" + type + ".png"));
		setPos(Map.getOffsetFromEdge() + Map.getTileSize()*posX, Map.getTileSize()*posY);
	}
	
	public void render (Batch batch) {
		sprite.draw(batch);
	}
	
	public void setPos(float posX, float posY) {
		sprite.setPosition(posX, posY);
	}
	
	public float[] getPos() {
		return new float[] {sprite.getX(), sprite.getY()};
	}

	public String getType() {
		return type;
	}
	
}
