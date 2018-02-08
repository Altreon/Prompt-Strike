package entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import main.Game;

public class Factory extends Structure{
	
	public Factory(String name, float posX, float posY) {
		super(name, new Sprite(Textures.getTexture(TEXTURE.Factory)), posX, posY);
	}

}
