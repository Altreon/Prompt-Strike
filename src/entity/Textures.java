package entity;

import java.util.Hashtable;

import com.badlogic.gdx.graphics.Texture;

public abstract class Textures {
	public static Hashtable<String, Texture> textures;
	
	public static void initialize () {
		textures = new Hashtable<String, Texture>();
		
		for(TEXTURE texture : TEXTURE.values()){
			textures.put(texture.toString(), new Texture(texture.toString()));
		}
	}
	
	public static Texture getTexture (TEXTURE texture) {
		return textures.get(texture.toString());
	}
}
