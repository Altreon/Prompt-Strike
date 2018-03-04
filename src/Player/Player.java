package Player;

import java.util.Hashtable;

import display.OutputScreen;
import entity.*;

public class Player {
	
	private int money;
	
	private Hashtable<String, Unit> units;
	
	private Hashtable<String, Structure> structures;
	
	public Player () {
		units = new Hashtable<String, Unit>();
		
		structures = new Hashtable<String, Structure>();
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
	
	public void addHeadquarter(String name, float posX, float posY) {
		structures.put(name, new Headquarter(name, posX, posY));
	}
	
	public void addFactory(String name, float posX, float posY) {
		structures.put(name, new Factory(name, posX, posY));
	}

	public void destroyEntity(String typeEntity, String nameEntity) {
		if(typeEntity.equals("Unit")) {
			units.remove(nameEntity);
		}else {
			structures.remove(nameEntity);
		}
		
	}
}
