package map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Map {
	
	Sprite grass;
	
	Sprite[][] map;
	
	public Map() {
		loadSprite();
		map = new Sprite[13][11];
		for (int i=0; i < map.length; i++) {
			for (int j=0; j < map[0].length; j++) {
				map[i][j] = new Sprite(new Texture("Tiles/grass.png"));
				map[i][j].setPosition(224 + 64*i, 64*j);
			}
		}
	}

	private void loadSprite() {
		grass = new Sprite(new Texture("Tiles/grass.png"));
	}
	
	public void render (Batch batch) {
		for (Sprite[] spriteLine : map) {
			for (Sprite sprite : spriteLine) {
				sprite.draw(batch);
			}
		}
	}

}
