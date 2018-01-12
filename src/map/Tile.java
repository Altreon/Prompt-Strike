package map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Tile {
	private String type;
	private Sprite sprite;
	
	public Tile (String type) {
		this.type = type;
		sprite = new Sprite(new Texture("Tiles/" + type + ".png"));
	}
	
	public void render (Batch batch) {
		sprite.draw(batch);
	}
	
	public void setPos(int posX, int posY) {
		sprite.setPosition(posX, posY);
	}

	public String getType() {
		return type;
	}
	
}
