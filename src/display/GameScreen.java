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
		int i = 0;
		for( Unit unit : Game.getAllUnits()) {
			System.out.println(i + " : " + unit.getPos()[0]);
			unit.render(batch);
			i++;
		}
		
		for (Effect effect : game.getEffects()) {
			effect.draw(batch);
		}
	}

}
