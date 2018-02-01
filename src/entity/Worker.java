package entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

import main.Game;
import map.Map;

public class Worker extends Unit{
	
	private static final int COST = 10;

	private Part sprite;
	
	public Worker (String name, float posX, float posY) {
		super(name);
		posX = posX + 224;
		//a changer...
		sprite = new Part(Game.getWorkerTexture());
		sprite.setPosition(posX, posY);
		
	}
	
	public static int getCost () {
		return COST;
	}
	
	@Override
	public float[] getPos() {
		return new float[]{sprite.getX(), sprite.getY()};
	}
	
	@Override
	public void setPos(float posX, float posY) {
		sprite.setPosition(posX, posY);
		
	}
	
	@Override
	public void render(Batch batch) {
		sprite.draw(batch);
		
	}

	@Override
	public void update(int dt) {
		
	}

	@Override
	protected float getRotation() {
		return sprite.getRotation();
	}
	
	@Override
	public void setRotation(float rotation) {
		sprite.setRotation(rotation);
	}

}
