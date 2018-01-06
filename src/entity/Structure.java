package entity;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public abstract class Structure extends Entity{
	
	protected int posX;
	protected int posY;
	
	private Sprite sprite;
	
	public Structure (Sprite sprite, int posX, int posY) {
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
	
}
