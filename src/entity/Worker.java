package entity;

import assets.TextureFiles;
import assets.Textures;

public class Worker extends Unit{
	
	private static final int COST = 10; // Use to show unit price on the future

	private Part body;
	
	public Worker (String name, float posX, float posY) {
		super(name, posX, posY, 1);
		
		body = new Part(Textures.getTexture(TextureFiles.Worker));
		body.setPosition(posX, posY);
		parts[0] = body;
		
	}
	
	public static int getCost () {
		return COST;
	}
}
