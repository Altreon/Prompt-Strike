package entity;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public abstract class Structure extends Entity{
	
	private Sprite building;
	
	public Structure (String name, Sprite building, float posX, float posY) {
		super(name, posX, posY);
		this.building = building;
				
		building.setPosition(posX, posY);
	}
	
	public void render(Batch batch) {
		building.draw(batch);
	}
	
	public void setPos(float posX, float posY) {
		super.setPos(posX, posY);
		building.setPosition(posX, posY);
	}
}
