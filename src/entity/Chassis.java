package entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Chassis extends Part{
	
	private Sprite attachPart;
	
	public Chassis (Sprite attachPart) {
		super(new Texture("Units/tank/chassis.png"));
		
		this.attachPart = attachPart;
	}
	
	@Override
	public void updateMove () {
		
	}
}
