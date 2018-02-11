package display;

import java.util.Enumeration;

import com.badlogic.gdx.graphics.g2d.Batch;

import effet.Effect;
import entity.Structure;
import entity.Unit;
import main.Game;
import map.Map;

public class GameScreen{
	
	private Game game;
	
	public GameScreen (Game game) {
		this.game = game;
	}
	
	public void render (Batch batch) {
		game.getMap().render(batch);
		
		for( Structure structure : Game.getAllstructures()) {
			structure.render(batch);
		}
		int i = 0;
		for( Unit unit : Game.getAllUnits()) {
			unit.render(batch);
			i++;
		}
		
		for (Effect effect : game.getEffects()) {
			effect.draw(batch);
		}
	}

}
