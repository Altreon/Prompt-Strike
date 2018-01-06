package effet;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import main.Game;

public class Effect extends Sprite{
	protected int duration;
	
	public Effect (Texture texture, float posX, float posY, float rotation) {
		super(texture);
		setPosition(posX, posY);
		setRotation(rotation);
	}
	
	public void timeDecrease (long currentTime) {
		duration -= currentTime;
		if (duration <= 0) {
			Game.destroyEffect(this);
		}
	}
}
