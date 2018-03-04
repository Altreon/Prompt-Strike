package effet;

import assets.TextureFiles;
import assets.Textures;

public class TankImpact extends Effect{
	
	private static final int DURATION = 100; // ???
	
	public TankImpact (float posX, float posY, float rotation) {
		super(Textures.getTexture(TextureFiles.TankImpact), posX, posY, rotation, DURATION);		
	}
}
