package main;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

import display.OutputScreen;
import effet.Effect;
import entity.Entity;
import entity.Factory;
import entity.Structure;
import entity.Tank;
import entity.Unit;
import entity.Worker;

public class Player {
	
	private int money;
	
	private static Hashtable<String, Unit> units;
	private static ArrayList<Unit> unitsToRemove;
	
	private static Hashtable<String, Structure> structures;
	private static ArrayList<Structure> structuresToRemove;
	
	public Player () {
		units = new Hashtable<String, Unit>();
		unitsToRemove = new ArrayList<Unit>();
		
		structures = new Hashtable<String, Structure>();
		structuresToRemove = new ArrayList<Structure>();
		
		money = 100;
	}
	
	public void addMoney(int amont) {
		money += amont;
		OutputScreen.updateMoney(money);
	}
	
	public void removeMoney(int amont) {
		money -= amont;
		OutputScreen.updateMoney(money);
	}
	
	public Hashtable<String, Unit> getUnits() {
		return units;
	}
	
	public Hashtable<String, Structure> getStructures() {
		return structures;
	}
	
	public boolean isUnit(String name) {
		return units.containsKey(name);
	}
	
	public boolean isStructure(String name) {
		return structures.containsKey(name);
	}
	
	public void unitSetPos(String name, float posX, float posY) {
		units.get(name).setPos(posX, posY);
	}
	
	public void unitSetRotation(String name, float rotation) {
		units.get(name).setRotation(rotation);
	}
	
	public boolean unitCanRotateCannon(String name) {
		return units.get(name).getClass().getSimpleName().equals("Tank");
	}
	
	public void rotateCannon(String name, int value) {
		((Tank) units.get(name)).rotateCannon(value);
	}
	
	public boolean unitCanFire(String name) {
		return units.get(name).getClass().getSimpleName().equals("Tank");
	}
	
	public void fireUnit(String name, int value, int playerOwner) {
		((Tank) units.get(name)).fire(value, playerOwner);
	}
	
	public boolean sufficientMoney(int cost) {
		return money >= cost;
	}
	
	public void addTank(String name, int posX, int posY) {
		units.put(name, new Tank(name, posX, posY));
		
	}
	
	public void addWorker(String name, int posX, int posY) {
		units.put(name, new Worker(name, posX, posY));
	}
	
	public void addFactory(String name, int posX, int posY) {
		structures.put(name, new Factory(name, posX, posY));
	}

	public void destroyEntity(Entity entity) {
		String entityClass = entity.getClass().getSuperclass().getSimpleName();
		if(entityClass.equals("Unit")) {
			units.remove(entity.getName());
		}else {
			structures.remove(entity.getName());
		}
		
	}
}
