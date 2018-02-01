package entity;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Tank extends Unit {
	
	private static final int COST = 20;
	
	private Chassis chassis;
	private Cannon cannon;
	
	private Part[] parts;

	public Tank (String name, float posX, float posY) {
		super(name);
		posX = posX * 64 + 224;
		posY *= 64;
		
		parts = new Part[2];
		
		cannon = new Cannon();
		cannon.setPosition(posX, posY);
		chassis = new Chassis(cannon);
		chassis.setPosition(posX, posY);
		
		parts[0] = chassis;
		parts[1] = cannon;
	}
	
	public static int getCost () {
		return COST;
	}
	
	@Override
	public void setPos(float posX, float posY) {
		chassis.setPosition(posX, posY);
	}
	
	@Override
	public float[] getPos() {
		return new float[]{chassis.getX(), chassis.getY()};
	}
	
	public Part[] getParts () {
		return parts;
	}
	
	public Cannon getCannon () {
		return cannon;
	}

	@Override
	public void render(Batch batch) {
		for (Sprite part : parts) {
			part.draw(batch);
		}
		
		//garder?
		cannon.update(chassis.getX(), chassis.getY());
	}
	
	@Override
	public void update(int dt) {
		
	}
	
	public void rotateCannon(int distance) {
		
	}

	public void fire(int distance, int playerOwner) {
		cannon.fire(distance, playerOwner);
	}
	
	@Override
	public void setRotation(float rotation) {
		chassis.setRotation(rotation);
	}

	@Override
	protected float getRotation() {
		return chassis.getRotation();
	}
}
