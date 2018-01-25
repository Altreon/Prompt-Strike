package entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import main.Game;

public class Factory extends Structure{
	
	public Factory(String name, int posX, int posY) {
		super(name, new Sprite(new Texture("Structures/factory.png")), posX, posY);
	}
	

	@Override
	public void update(int dt) {
		
	}

}
