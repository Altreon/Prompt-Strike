package effet;

import assets.TextureFiles;
import assets.Textures;

public class TankFire extends Effect{
	private static final int DURATION = 100;
	
	public TankFire (float posX, float posY, float rotation) {
		super(Textures.getTexture(TextureFiles.TankFire), posX, posY, rotation, DURATION);
	}
}
