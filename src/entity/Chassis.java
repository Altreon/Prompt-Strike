package entity;

import com.badlogic.gdx.graphics.g2d.Sprite;

import assets.TextureFiles;
import assets.Textures;

/** 
 * The main part of the tank unit
 * 
 * @see Unit
 * */
public class Chassis extends Part{
		
	/**
     * Create a chassis
     * 
     * @param owner
     * 				The player ID who owns the cannon
     * @param commandText
     * 				The initial cannon's rotation
     */
	public Chassis (Sprite attachPart) {
		super(Textures.getTexture(TextureFiles.TankChassis));
	}
}
