package entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Chassis extends Part{
	
	private Sprite attachPart;
	
	public Chassis (Sprite attachPart) {
		super(new Texture("Units/tank/chassis.png"));
		
		SPEEDMOVE = 1;
		SPEEDROTATE = 1;
		
		this.attachPart = attachPart;
	}
	
	@Override
	public void updateMove () {
		float moveX = (float) (getX() + SPEEDMOVE * moveDirection * Math.cos(Math.toRadians(getRotation())));
		float moveY = (float) (getY() + SPEEDMOVE * moveDirection * Math.sin(Math.toRadians(getRotation())));
		moveDistance -= (float) (dist(getX(), getY(), moveX, moveY));
		attachPart.setX(moveX);
		attachPart.setY(moveY);
		setX(moveX);
		setY(moveY);
		if(moveDistance <= 0) {
			moveDistance = 0;
		}
	}
}
