package entity;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Tank extends Unit {
	
	private static final int COST = 20;
	
	private Chassis chassis;
	private Cannon cannon;

	public Tank (String name, float posX, float posY) {
		super(name, posX, posY, 2);
		posX = posX + 224;
		
		cannon = new Cannon();
		cannon.setPosition(posX, posY);
		chassis = new Chassis(cannon);
		chassis.setPosition(posX, posY);
		parts = new Part[] {chassis, cannon};
		
		parts[0] = chassis;
		parts[1] = cannon;
	}
	
	public static int getCost () {
		return COST;
	}
	
	public Cannon getCannon () {
		return cannon;
	}

	@Override
	public void render(Batch batch) {
		super.render(batch);
		
		//garder?
		cannon.update(chassis.getX(), chassis.getY());
	}

	/*public void fire(int distance, int playerOwner) {
		cannon.fire(distance, playerOwner);
	}*/

	public void fire() {
		cannon.fire();
		
	}
}
