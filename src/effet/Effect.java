package effet;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import game.Game;

public class Effect extends Sprite{
	
	protected int duration;
	
	public Effect (Texture texture, float posX, float posY, float rotation, int duration) {
		super(texture);
		
		setPosition(posX, posY);
		setRotation(rotation);
		
		this.duration = duration;
	}
	
	public void timeDecrease (int currentTime) {
		duration -= currentTime;
		if (duration <= 0) {
			Game.destroyEffect(this);
		}
	}
}
