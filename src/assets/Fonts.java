package assets;

import java.util.Hashtable;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class Fonts {
public static Hashtable<String, Skin> fonts;
	
	public static void initialize () {
		fonts = new Hashtable<String, Skin>();
		
		for(FontFiles font : FontFiles.values()){
			fonts.put(font.toString(), new Skin(Gdx.files.internal(font.toString())));
		}
	}
	
	public static Skin getFont (FontFiles font) {
		return fonts.get(font.toString());
	}
}
