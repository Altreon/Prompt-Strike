package effet;

import com.badlogic.gdx.graphics.Texture;

public class TankImpact extends Effect{
	
	public TankImpact (float posX, float posY, float rotation) {
		super(new Texture("effects/tankImpact.png"), posX, posY, rotation);
		
		duration = 10000;
	}
}
