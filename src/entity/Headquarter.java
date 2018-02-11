package entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import main.Game;

public class Headquarter extends Structure{
	
	public Headquarter(String name, float posX, float posY) {
		super(name, new Sprite(Textures.getTexture(TEXTURE.Headquarter)), posX, posY);
	}

}
