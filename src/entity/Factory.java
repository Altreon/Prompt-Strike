package entity;

import com.badlogic.gdx.graphics.g2d.Sprite;

import assets.TextureFiles;
import assets.Textures;

public class Factory extends Structure{
	
	private static final int COST = 50; // Use to show unit price on the future
	
	public Factory(String name, float posX, float posY) {
		super(name, new Sprite(Textures.getTexture(TextureFiles.Factory)), posX, posY);
	}
	
	public static int getCost () {
		return COST;
	}

}
