package map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Map {
	
	Sprite grass;
	
	static Tile[][] map;
	
	public Map() {
		loadSprite();
		map = new Tile[13][11];
		for (int i=0; i < map.length; i++) {
			for (int j=0; j < map[0].length; j++) {
				map[i][j] = new Tile("grass");
				map[i][j].setPos(224 + 64*i, 64*j);
			}
		}
	}

	private void loadSprite() {
		grass = new Sprite(new Texture("Tiles/grass.png"));
	}
	
	public void render (Batch batch) {
		for (Tile[] tileLine : map) {
			for (Tile tile : tileLine) {
				tile.render(batch);
			}
		}
	}
	
	static public String getTileType(int posX, int posY) {
		return map[posX][posY].getType();
	}

}
