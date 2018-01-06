package effet;

import com.badlogic.gdx.graphics.Texture;

public class TankFire extends Effect{
	
	public TankFire (float posX, float posY, float rotation) {
		super(new Texture("effects/tankFire.png"), posX, posY, rotation);
		
		duration = 100;
	}
}
