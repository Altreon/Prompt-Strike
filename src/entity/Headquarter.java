package entity;

import com.badlogic.gdx.graphics.g2d.Sprite;

import assets.TextureFiles;
import assets.Textures;

public class Headquarter extends Structure{
		
	public Headquarter(String name, float posX, float posY) {
		super(name, new Sprite(Textures.getTexture(TextureFiles.Headquarter)), posX, posY);
	}

}
