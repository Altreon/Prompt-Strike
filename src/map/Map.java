package map;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.Batch;

public class Map {
	
	private static final int MAP_SIZE_X = 13;
	private static final int MAP_SIZE_Y = 11;
	
	private static final int TILE_SIZE = 64;
	
	private static final int OFFSET_FROM_EDGE = 224;
			
	private static Tile[][] map;
	private static Ressource[][] ressources;
	
	public Map() {
		map = new Tile[MAP_SIZE_X][MAP_SIZE_Y];
		
		//fill all map with grass tiles
		for (int i=0; i < map.length; i++) {
			for (int j=0; j < map[i].length; j++) {
				map[i][j] = new Tile("grass", i, j);
			}
		}
		
		ressources = new Ressource[MAP_SIZE_X][MAP_SIZE_Y];
		
		//add two crystal at specific position
		ressources[1][1] = new Ressource("crystal", 1, 1);
		ressources[MAP_SIZE_X-1][MAP_SIZE_Y-1] = new Ressource("crystal", MAP_SIZE_X-2, MAP_SIZE_Y-2);
		
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
			return "nothing";
		}
	}
	
	public static int getMapSizeX () {
		return MAP_SIZE_X;
	}
	
	public static int getMapSizeY () {
		return MAP_SIZE_Y;
	}
	
	public static int getTileSize () {
		return TILE_SIZE;
	}
	
	public static int getOffsetFromEdge () {
		return OFFSET_FROM_EDGE;
	}

}
