package assets;

import java.util.Hashtable;

import com.badlogic.gdx.graphics.Texture;

public abstract class Textures {
	public static Hashtable<String, Texture> textures;
	
	public static void initialize () {
		textures = new Hashtable<String, Texture>();
		
		for(TextureFiles texture : TextureFiles.values()){
			textures.put(texture.toString(), new Texture(texture.toString()));
		}
	}
	
	public static Texture getTexture (TextureFiles texture) {
		return textures.get(texture.toString());
	}
}
