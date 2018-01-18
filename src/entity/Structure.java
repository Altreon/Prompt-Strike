package entity;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public abstract class Structure extends Entity{
	
	protected int posX;
	protected int posY;
	
	private Sprite sprite;
	
	public Structure (String name, Sprite sprite, int posX, int posY) {
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
	
}
