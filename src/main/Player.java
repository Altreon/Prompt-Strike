package main;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

import display.OutputScreen;
import effet.Effect;
import effet.TankFire;
import entity.Entity;
import entity.Factory;
import entity.Structure;
import entity.Tank;
import entity.Unit;
import entity.Worker;

public class Player {
	
	private int money;
	
	private Hashtable<String, Unit> units;
	private ArrayList<Unit> unitsToRemove;
	
	private Hashtable<String, Structure> structures;
	private ArrayList<Structure> structuresToRemove;
	
	public Player () {
		units = new Hashtable<String, Unit>();
		unitsToRemove = new ArrayList<Unit>();
		
		structures = new Hashtable<String, Structure>();
		structuresToRemove = new ArrayList<Structure>();
	}
	
	public void updateMoney(int amount) {
		money = amount;
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
	
	public void unitSetRotation(String name, float rotation, int idPart) {
		units.get(name).setRotation(rotation, idPart);
	}
	
	public boolean unitCanRotateCannon(String name) {
		return units.get(name).getClass().getSimpleName().equals("Tank");
	}
	
	public boolean unitCanFire(String name) {
		return units.get(name).getClass().getSimpleName().equals("Tank");
	}
	
	/*public void fireUnit(String nameUnit, int value, int playerOwner) {
		((Tank) units.get(name)).fire(value, playerOwner);
	}*/
	
	public void fireUnit(String name) {
		((Tank) units.get(name)).fire();
	}
	
	public boolean sufficientMoney(int cost) {
		return money >= cost;
	}
	
	public void addTank(String name, float posX, float posY) {
		units.put(name, new Tank(name, posX, posY));
		
	}
	
	public void addWorker(String name, float f, float g) {
		units.put(name, new Worker(name, f, g));
	}
	
	public void addFactory(String name, float posX, float posY) {
		structures.put(name, new Factory(name, posX, posY));
	}

	/*public void destroyEntity(Entity entity) {
		String entityClass = entity.getClass().getSuperclass().getSimpleName();
		if(entityClass.equals("Unit")) {
			units.remove(entity.getName());
		}else {
			structures.remove(entity.getName());
		}
		
	}*/

	public void destroyEntity(String typeEntity, String nameEntity) {
		if(typeEntity.equals("Unit")) {
			units.remove(nameEntity);
		}else {
			structures.remove(nameEntity);
		}
		
	}
}
