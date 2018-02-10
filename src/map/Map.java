package map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Map {
	
	Sprite grass;
	
	static Tile[][] map;
	static Ressource[][] ressources;
	
	public Map() {
		map = new Tile[13][11];
		for (int i=0; i < map.length; i++) {
			for (int j=0; j < map[0].length; j++) {
				map[i][j] = new Tile("grass");
				map[i][j].setPos(224 + 64*i, 64*j);
			}
		}
		
		ressources = new Ressource[13][11];
		ressources[4][0] = new Ressource("crystal", 4, 0);
		
	}
	
	public void render (Batch batch) {
		for (Tile[] tileLine : map) {
			for (Tile tile : tileLine) {
				tile.render(batch);
			}
		}
		
		for (Ressource[] resLine : ressources) {
			for (Ressource res : resLine) {
				if(res != null) {
					res.render(batch);
				}
			}
		}
	}
	
	static public String getRessourceType(int posX, int posY) {
		if(ressources[posX][posY] != null) {
			return ressources[posX][posY].getType();
		}else {
			return "";
		}
	}

}
