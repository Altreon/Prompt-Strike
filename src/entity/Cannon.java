package entity;

import assets.TextureFiles;
import assets.Textures;
import effet.TankFire;
import game.Game;

public class Cannon extends Part{
	
	//bad way to to an fire animation, but lack of time.
	private final int ESTIMATE_TIME = 330;
	private long startTime;

	public Cannon () {
		super(Textures.getTexture(TextureFiles.TankCannon));
	}
	
	public void update (float x, float y) {
		if(x != getX() || y != getY()) {
			if(System.currentTimeMillis() - startTime >= ESTIMATE_TIME) {
				setPosition(x, y);
			}else {
				//Unfortunately, sprite store rotation in degree, so...
				setX((float) (getX() + getWidth()/100*Math.cos(Math.toRadians(getRotation()))));
				setY((float) (getY() + getHeight()/100*Math.sin(Math.toRadians(getRotation()))));
			}
		}
	}

	public void fire() {
		//Unfortunately, sprite store rotation in degree, so...
		
		float firePosX = (float) (getX() + getWidth()/1.3f*Math.cos(Math.toRadians(getRotation())));
		float firePosY = (float) (getY() + getHeight()/1.3f*Math.sin(Math.toRadians(getRotation())));
		
		Game.createEffect(new TankFire(firePosX, firePosY, getRotation() - 90));
	
		setX((float) (getX() - getWidth()/5*Math.cos(Math.toRadians(getRotation()))));
		setY((float) (getY() - getHeight()/5*Math.sin(Math.toRadians(getRotation()))));
		
		startTime = System.currentTimeMillis();
		
	}
}