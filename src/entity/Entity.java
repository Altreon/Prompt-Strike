package entity;

import com.badlogic.gdx.graphics.g2d.Batch;

public abstract class Entity {
	
	private String name;
	private int HP;
	
	public abstract void render(Batch batch);
	public abstract void update(int dt);
	public abstract void setPos(float posX, float posY);
	public abstract float[] getPos();
	
	public Entity(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public int getHP () {
		return HP;
	}
	
	public void changeHP(int amount) {
		HP += amount;
	}
}
