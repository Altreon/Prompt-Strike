package entity;

import com.badlogic.gdx.graphics.g2d.Batch;

public abstract class Entity {
	
	private int HP;
	
	public abstract void render(Batch batch);
	public abstract void update();
}
