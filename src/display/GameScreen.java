package display;

import com.badlogic.gdx.graphics.g2d.Batch;

import effet.Effect;
import entity.Structure;
import entity.Unit;
import game.Game;

public class GameScreen{
	
	private Game game;
	
	public GameScreen (Game game) {
		this.game = game;
	}
	
	public void render (Batch batch) {
		game.getMap().render(batch);
		
		//Render in this order : effects -> structures -> units
		
		for (Effect effect : game.getEffects()) {
			effect.draw(batch);
		}
		
		for( Structure structure : Game.getAllstructures()) {
			structure.render(batch);
		}

		for( Unit unit : Game.getAllUnits()) {
			unit.render(batch);
		}
	}

}
