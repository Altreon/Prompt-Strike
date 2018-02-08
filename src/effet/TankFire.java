package effet;

import com.badlogic.gdx.graphics.Texture;

import entity.TEXTURE;
import entity.Textures;

public class TankFire extends Effect{
	
	public TankFire (float posX, float posY, float rotation) {
		super(Textures.getTexture(TEXTURE.TankFire), posX, posY, rotation);
		
		duration = 100;
	}
}
