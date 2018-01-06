package main;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;


import effet.Effect;
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
	
	public Hashtable<String, Unit> getAllUnits() {
		Hashtable<String, Unit> units = new Hashtable<String, Unit>();
		for(Player player : players) {
			units.putAll(player.getUnits());
		}
		return units;
	}
	
	public Hashtable<String, Structure> getAllstructures() {
		Hashtable<String, Structure> structures = new Hashtable<String, Structure>();
		for(Player player : players) {
			structures.putAll(player.getStructures());
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
		long time = currentTime - lastTime;
		
		Enumeration<Structure> structuresEnum = getAllstructures().elements();
		while(structuresEnum.hasMoreElements()) {
			Structure structure = structuresEnum.nextElement();
			structure.update();
		}
		
		Enumeration<Unit> unitsEnum = getAllUnits().elements();
		while(unitsEnum.hasMoreElements()) {
			Unit unit = unitsEnum.nextElement();
			unit.update();
		}
		
		for (Effect effect : effects) {
			effect.timeDecrease(time);
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

	public static void createTank(String name, int posX, int posY) {
		players.get(0).addTank(name, posX, posY);
	}
	
	public static void createWorker(String name, int posX, int posY) {
		players.get(0).addWorker(name, posX, posY);
	}
}
