package main;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;


import effet.Effect;
import entity.Entity;
import entity.Part;
import entity.Structure;
import entity.Unit;

public class Game {
	
	private static ArrayList<Player> players;
	
	private static ArrayList<Effect> effects;
	private static ArrayList<Effect> effectsToRemove;
	
	private long lastTime;
	
	public Game () {
		players = new ArrayList<Player>();
		players.add(new Player());
		
		effects = new ArrayList<Effect>();
		effectsToRemove = new ArrayList<Effect>();
		
		lastTime = System.currentTimeMillis();;
	}
	
	public ArrayList<Unit> getAllUnits() {
		ArrayList<Unit> units = new ArrayList<Unit>();
		for(Player player : players) {
			Enumeration<Unit> unitsEnum = player.getUnits().elements();
			while(unitsEnum.hasMoreElements()) {
				units.add(unitsEnum.nextElement());
			}
		}
		return units;
	}
	
	public ArrayList<Structure> getAllstructures() {
		ArrayList<Structure> structures = new ArrayList<Structure>();
		for(Player player : players) {
			Enumeration<Structure> structuresEnum = player.getStructures().elements();
			while(structuresEnum.hasMoreElements()) {
				structures.add(structuresEnum.nextElement());
			}
		}
		return structures;
	}
	
	public static ArrayList<Player> getPlayers() {
		return players;
	}
	
	public ArrayList<Effect> getEffects() {
		return effects;
	}
	
	public static void createEffect(Effect effect) {
		effects.add(effect);
	}
	
	public static void destroyEffect(Effect effect) {
		effectsToRemove.add(effect);
	}

	public void update() {
		long currentTime = System.currentTimeMillis();
		int dt = (int) (System.currentTimeMillis() - lastTime);
		
		for( Structure structure : getAllstructures()) {
			structure.update(dt);
		}
		
		for( Unit unit : getAllUnits()) {
			unit.update(dt);
		}
		
		for (Effect effect : effects) {
			effect.timeDecrease(dt);
		}
		
		destroyEntities();
		
		lastTime = currentTime;
	}
	
	private void destroyEntities () {

		for (Effect effect : effectsToRemove) {
			effects.remove(effect);
		}
		
		effectsToRemove.clear();
	}
	
	public static void applyDamage(float posX, float posY, int radius, int amount) {
		//ArrayList<Entity> entityTouched = new ArrayList<Entity>;
		//for (Unit unit : getAllUnits()) {
				
			
		//}
		
	}

	public static void createTank(String name, int posX, int posY) {
		players.get(0).addTank(name, posX, posY);
	}
	
	public static void createWorker(String name, int posX, int posY) {
		players.get(0).addWorker(name, posX, posY);
	}

	public static void createFactory(String name, int posX, int posY) {
		players.get(0).addFactory(name, posX, posY);
	}
}
