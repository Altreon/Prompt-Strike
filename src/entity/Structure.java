package entity;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public abstract class Structure extends Entity{
	
	private Sprite building;
	
	public Structure (String name, Sprite building, float posX, float posY) {
		super(name, posX, posY);
		this.building = building;
		
		posX = posX + 224;
		
		building.setPosition(posX, posY);
	}
	
	public void render(Batch batch) {
		building.draw(batch);
	}
}
