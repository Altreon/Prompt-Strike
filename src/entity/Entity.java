package entity;

import com.badlogic.gdx.graphics.g2d.Batch;

public abstract class Entity {
	
	private String name;
	private int HP;
	
	protected float[] pos;
	
	public abstract void render(Batch batch);
	//public abstract void update(int dt);
	//public abstract void setPos(float posX, float posY);
	
	public Entity(String name, float posX, float posY) {
		this.name = name;
		
		pos = new float[2];
		pos[0] = posX;
		pos[1] = posY;
	}
	
	public String getName() {
		return name;
	}
	
	public void setPos(float posX, float posY) {
		pos[0] = posX;
		pos[1] = posY;
		
	}
	
	public float[] getPos() {
		return pos;
	}
	
	public int getHP () {
		return HP;
	}
	
	public void changeHP(int amount) {
		HP += amount;
	}
}
