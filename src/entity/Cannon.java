package entity;

import com.badlogic.gdx.graphics.Texture;

import effet.TankFire;
import effet.TankImpact;
import main.Game;

public class Cannon extends Part{
	
	//garder?
	private final int ESTIMATETIME = 330;
	private long startTime;

	public Cannon () {
		super(new Texture("Units/tank/cannon.png"));
		
		SPEEDMOVE = 0;
		SPEEDROTATE = 1;
	}
	
	@Override
	public boolean isMoving () {
		return false;
	}
	
	//garder?
	public void update (float x, float y) {
		if(x != getX() || y != getY()) {
			if(System.currentTimeMillis() - startTime >= ESTIMATETIME) {
				setPosition(x, y);
			}else {
				setX((float) (getX() + getWidth()/100*Math.cos(Math.toRadians(getRotation()))));
				setY((float) (getY() + getHeight()/100*Math.sin(Math.toRadians(getRotation()))));
			}
		}
	}

	public void fire(int distance) {
		Game.createEffect(new TankFire((float) (getX() + getWidth()/1.3f*Math.cos(Math.toRadians(getRotation()))), (float) (getY() + getHeight()/1.3f*Math.sin(Math.toRadians(getRotation()))), getRotation() - 90));
		Game.createEffect(new TankImpact((float) (getX() + distance*64*Math.cos(Math.toRadians(getRotation()))), (float) (getY() + distance*64*Math.sin(Math.toRadians(getRotation()))), 0));
	
		setX((float) (getX() - getWidth()/5*Math.cos(Math.toRadians(getRotation()))));
		setY((float) (getY() - getHeight()/5*Math.sin(Math.toRadians(getRotation()))));
		
		startTime = System.currentTimeMillis();
	}
}