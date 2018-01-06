package entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Part extends Sprite{
	protected float SPEEDMOVE;
	protected float SPEEDROTATE;
	
	protected float moveDistance;
	protected float moveDirection;
	protected float rotateDistance;
	protected float rotateDirection;
	
	public Part (Texture texture) {
		super(texture);
	}
	
	public boolean isMoving () {
		if (moveDistance == 0) {
			return false;
		}else {
			return true;
		}
	}
	
	public boolean isRotating () {
		if (rotateDistance == 0) {
			return false;
		}else {
			return true;
		}
	}
	
	public void updateMove () {
		float moveX = (float) (getX() + SPEEDMOVE * moveDirection * Math.cos(Math.toRadians(getRotation())));
		float moveY = (float) (getY() + SPEEDMOVE * moveDirection * Math.sin(Math.toRadians(getRotation())));
		moveDistance -= (float) (dist(getX(), getY(), moveX, moveY));
		setX(moveX);
		setY(moveY);
		if(moveDistance <= 0) {
			moveDistance = 0;
		}
	}
	
	public void updateRotate () {
		float rotation = getRotation() + SPEEDROTATE * rotateDirection;
		rotateDistance -= (rotation - getRotation()) * rotateDirection;
		setRotation(rotation);
		if(rotateDistance <= 0) {
			rotateDistance = 0;
		}
	}
	
	protected double dist(float x1, float y1, float x2, float y2) {
		return Math.sqrt(Math.pow(x2-x1, 2) + Math.pow(y2-y1, 2));
	}
}