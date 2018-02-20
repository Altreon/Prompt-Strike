package map;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Map {
	
	private Sprite grass;
	
	private Tile[][] map;
	private static Ressource[][] ressources;
	
	public Map() {
		map = new Tile[13][11];
		for (int i=0; i < map.length; i++) {
			for (int j=0; j < map[0].length; j++) {
				map[i][j] = new Tile("grass");
				map[i][j].setPos(224 + 64*i, 64*j);
			}
		}
		
		ressources = new Ressource[13][11];
		ressources[1][1] = new Ressource("crystal", 1, 1);
		ressources[11][9] = new Ressource("crystal", 11, 9);
		
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
	
	public ArrayList<Tile> getTile() {
		ArrayList<Tile> tiles = new ArrayList<Tile>();
		
		for (int i=0; i < map.length; i++) {
			for (int j=0; j < map[0].length; j++) {
				tiles.add(map[i][j]);
			}
		}
		
		for (int i=0; i < ressources.length; i++) {
			for (int j=0; j < ressources[0].length; j++) {
				if(ressources[i][j] != null) {
					tiles.add(ressources[i][j]);
				}
			}
		}
		
		return tiles;
		
	}
	
	public static String getRessourceType(int posX, int posY) {
		if(ressources[posX][posY] != null) {
			return ressources[posX][posY].getType();
		}else {
			return "";
		}
	}

}
