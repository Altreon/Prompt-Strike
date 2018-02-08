package entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

import main.Game;
import map.Map;

public class Worker extends Unit{
	
	private static final int COST = 10;

	private Part body;
	
	public Worker (String name, float posX, float posY) {
		super(name, posX, posY, 1);
		posX = posX + 224;
		
		body = new Part(Textures.getTexture(TEXTURE.Worker));
		body.setPosition(posX, posY);
		parts[0] = body;
		
	}
	
	public static int getCost () {
		return COST;
	}
}
