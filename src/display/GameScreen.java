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
	private Map map;
	
	public GameScreen (Game game) {
		this.game = game;
		map = new Map();
	}
	
	public void render (Batch batch) {
		map.render(batch);
		
		for( Structure structure : Game.getAllstructures()) {
			structure.render(batch);
		}
		
		for( Unit unit : Game.getAllUnits()) {
			unit.render(batch);
		}
		
		for (Effect effect : game.getEffects()) {
			effect.draw(batch);
		}
	}

}
