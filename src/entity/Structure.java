package entity;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public abstract class Structure extends Entity{
	
	protected float posX;
	protected float posY;
	
	private Sprite sprite;
	
	public Structure (String name, Sprite sprite, float posX, float posY) {
		super(name);
		this.sprite = sprite;
		
		this.posX = posX;
		this.posY = posY;
		
		posX = posX * 64 + 224;
		posY *= 64;
		
		sprite.setPosition(posX, posY);
	}
	
	@Override
	public void render(Batch batch) {
		sprite.draw(batch);
	}
	
	@Override
	public float[] getPos() {
		return new float[]{sprite.getX(), sprite.getY()};
	}
	
	@Override
	public void setPos(float posX, float posY) {
		sprite.setPosition(posX, posY);
	}
	
}
