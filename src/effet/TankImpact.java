package effet;

import com.badlogic.gdx.graphics.Texture;

import entity.TEXTURE;
import entity.Textures;

public class TankImpact extends Effect{
	
	public TankImpact (float posX, float posY, float rotation) {
		super(Textures.getTexture(TEXTURE.TankImpact), posX, posY, rotation);
		
		duration = 10000;
	}
}
