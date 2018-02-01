package main;

import java.util.ArrayList;
import java.util.Enumeration;

import com.badlogic.gdx.graphics.Texture;

import effet.Effect;
import entity.Entity;
import entity.Structure;
import entity.Unit;
import math.MATH;
import message.*;
import network.Network;

public class Game {
	
	private static ArrayList<Player> players;
	
	private static ArrayList<Effect> effects;
	private static ArrayList<Effect> effectsToRemove;
	
	private long lastTime;
	
	private static Network network;
	
	//temporaire
	private static Texture workerTexture;
	
	
	public Game () {
		players = new ArrayList<Player>();
		//players.add(new Player());
		
		effects = new ArrayList<Effect>();
		effectsToRemove = new ArrayList<Effect>();
		
		network = new Network();
		
		workerTexture = new Texture("Units/worker.png");
		
		lastTime = System.currentTimeMillis();
	}
	
	//temporaire;
	public static Texture getWorkerTexture () {
		return workerTexture;
	}
	
	public static boolean inGame() {
		return network.isConnected();
	}
	
	
	public static void connectServer() {
		network.connect();
	}
	
	public static void sendCommand(String command) {
		network.sendCommand(command);
	}
	
	public static ArrayList<Unit> getAllUnits() {
		ArrayList<Unit> units = new ArrayList<Unit>();
		for(Player player : players) {
			Enumeration<Unit> unitsEnum = player.getUnits().elements();
			while(unitsEnum.hasMoreElements()) {
				units.add(unitsEnum.nextElement());
			}
		}
		return units;
	}
	
	public static ArrayList<Structure> getAllstructures() {
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
	
	public static void addMoney(int amont) {
		players.get(0).addMoney(amont);
	}
	
	public static void removeMoney(int amont) {
		players.get(0).removeMoney(amont);
	}
	
	public static void applyDamage(float posX, float posY, int radius, int amount, int playerExluded) {
		ArrayList<Entity> entityTouched = new ArrayList<Entity>();
		for (Unit unit : getAllUnits()) {
			float[] posDamageUnit = {unit.getPos()[0] - posX, unit.getPos()[1] - posY};
			if(!players.get(playerExluded).isUnit(unit.getName()) && MATH.norme(posDamageUnit) <= radius*64) {
				System.out.println("touch!");;
				entityTouched.add(unit);
			}
			
		}
		
		for (Structure struct : getAllstructures()) {
			float[] posDamageStruct = {struct.getPos()[0] - posX, struct.getPos()[1] - posY};
			if(!players.get(playerExluded).isStructure(struct.getName()) && MATH.norme(posDamageStruct) <= radius*64) {
				System.out.println("touch!");
				entityTouched.add(struct);
			}
			
		}
		
		for (Entity entity : entityTouched) {
			entity.changeHP(-amount);
			if(entity.getHP() <= 0) {
				players.get(0).destroyEntity(entity);
			}
		}
		
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

	public static void processMessage(Message messageReceived) {
		String messageType = messageReceived.getClass().getSimpleName();
		
		if(messageType.equals("CommandMessage")) {
			CommandMessage message = (CommandMessage) messageReceived;
			PromptStrike.getInputScreen().dispCommand(message.getCommand(), message.getCorrect());
		}else if(messageType.equals("NewPlayerMessage")) {
			NewPlayerMessage message = (NewPlayerMessage) messageReceived;
			players.add(new Player());
		}else if(messageType.equals("PosMessage")) {
			PosMessage message = (PosMessage) messageReceived;
			players.get(message.getNumPlayer()).unitSetPos(message.getNameUnit(), message.getPosX(), message.getPosY());
		}else if(messageType.equals("RotMessage")) {
			RotMessage message = (RotMessage) messageReceived;
			players.get(message.getNumPlayer()).unitSetRotation(message.getNameUnit(), message.getRotation());
		}else if(messageType.equals("CreateEntityMessage")) {
			CreateEntityMessage message = (CreateEntityMessage) messageReceived;
			if(message.getTypeEntity().equals("worker")) {
				players.get(message.getNumPlayer()).addWorker(message.getNameEntity(), message.getPosX(), message.getPosY());
			}else if(message.getTypeEntity().equals("tank")) {
				players.get(message.getNumPlayer()).addTank(message.getNameEntity(), message.getPosX(), message.getPosY());
			}else if(message.getTypeEntity().equals("factory")) {
				players.get(message.getNumPlayer()).addFactory(message.getNameEntity(), message.getPosX(), message.getPosY());
			}
		}
		
	}
}
