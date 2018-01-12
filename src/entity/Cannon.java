package entity;

import com.badlogic.gdx.graphics.Texture;

import effet.TankFire;
import effet.TankImpact;
import main.Game;

public class Cannon extends Part{
	
	//garder?
	private final int ESTIMATETIME = 330;
	private long startTime;
	
	private final int IMPACTRADIUS = 3;
	private final int IMPACTDAMAGE = 1;

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
		float firePosX = (float) (getX() + getWidth()/1.3f*Math.cos(Math.toRadians(getRotation())));
		float firePosY = (float) (getY() + getHeight()/1.3f*Math.sin(Math.toRadians(getRotation())));
		
		float ImpactPosX = (float) (getX() + distance*64*Math.cos(Math.toRadians(getRotation())));
		float ImpactPosY = (float) (getY() + distance*64*Math.sin(Math.toRadians(getRotation())));
		
		Game.applyDamage(ImpactPosX, ImpactPosY, IMPACTRADIUS, IMPACTDAMAGE);
		Game.createEffect(new TankFire(firePosX, firePosY, getRotation() - 90));
		Game.createEffect(new TankImpact(ImpactPosX, ImpactPosY, 0));
	
		setX((float) (getX() - getWidth()/5*Math.cos(Math.toRadians(getRotation()))));
		setY((float) (getY() - getHeight()/5*Math.sin(Math.toRadians(getRotation()))));
		
		startTime = System.currentTimeMillis();
	}
}