package main;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

import effet.Effect;
import entity.Factory;
import entity.Structure;
import entity.Tank;
import entity.Unit;
import entity.Worker;

public class Player {
	
	private static Hashtable<String, Unit> units;
	private static ArrayList<Unit> unitsToRemove;
	
	private static Hashtable<String, Structure> structures;
	private static ArrayList<Structure> structuresToRemove;
	
	public Player () {
		units = new Hashtable<String, Unit>();
		unitsToRemove = new ArrayList<Unit>();
		
		structures = new Hashtable<String, Structure>();
		structuresToRemove = new ArrayList<Structure>();
		
		units.put("tank", new Tank(1,1));
		units.put("worker", new Worker(2,2));
		
		structures.put("factory", new Factory(3,3));
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
	
	public void moveUnit(String name, int value) {
		units.get(name).move(value);
	}
	
	public void moveUnit(String name, int posX, int posY) {
		units.get(name).move(posX, posY);
	}
	
	public void rotateUnit(String name, int value) {
		units.get(name).rotate(value);
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
	
	public void fireUnit(String name, int value) {
		((Tank) units.get(name)).fire(value);
	}
	
	public boolean unitCanBuild(String unitName, String structType, String structName) {
		return units.get(unitName).getClass().getSimpleName().equals("Worker")
				&& !structures.containsKey(structName)
				&& ((Worker) units.get(unitName)).canBuild(structType);
	}
	
	public void buildUnit(String unitName, String structType, String structName) {
		((Worker) units.get(unitName)).build(structType, structName);
		
	}

	public boolean structCanProduce(String structName, String unitType, String unitName) {
		return structures.get(structName).getClass().getSimpleName().equals("Factory")
				&& !units.containsKey(unitName)
				&& ((Factory) structures.get(structName)).canProduce(unitType); //à généralisé dans class Structure
	}

	public void structProduce(String structName, String unitType, String unitName) {
		((Factory) structures.get(structName)).produce(unitType, unitName);
		
	}
	
	public void addTank(String name, int posX, int posY) {
		units.put(name, new Tank(posX, posY));
		
	}
	
	public void addWorker(String name, int posX, int posY) {
		units.put(name, new Worker(posX, posY));
	}
	
	public void addFactory(String name, int posX, int posY) {
		structures.put(name, new Factory(posX, posY));
	}
}
