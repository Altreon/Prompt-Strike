package entity;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Tank extends Unit {
	
	private static final int COST = 20;
	
	private Chassis chassis;
	private Cannon cannon;
	
	private Part[] parts;

	public Tank (String name, int posX, int posY) {
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
		for(Part part : parts) {
			if(part.isMoving()){
				part.updateMove();
			}
			if(part.isRotating()){
				part.updateRotate();
			}
		}
		
		if(!chassis.isRotating() && waitMoveDistance != 0){
			move(waitMoveDistance);
			waitMoveDistance = 0;
		}
	}
	
	@Override
	public void move(int distance) {
		if(distance > 0) {
			chassis.moveDistance = distance*64;
			chassis.moveDirection = 1;
		}else {
			chassis.moveDistance = -distance*64;
			chassis.moveDirection = -1;
		}
	}
	
	@Override
	public void rotate(int distance) {
		if(distance > 0) {
			chassis.rotateDistance = distance;
			chassis.rotateDirection = 1;
		}else {
			chassis.rotateDistance = -distance;
			chassis.rotateDirection = -1;
		}
	}
	
	public void rotateCannon(int distance) {
		if(distance > 0) {
			cannon.rotateDistance = distance;
			cannon.rotateDirection = 1;
		}else {
			cannon.rotateDistance = -distance;
			cannon.rotateDirection = -1;
		}
	}

	public void fire(int distance, int playerOwner) {
		cannon.fire(distance, playerOwner);
	}

	@Override
	protected float getRotation() {
		return chassis.getRotation();
	}
}
