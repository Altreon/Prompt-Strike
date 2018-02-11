package main;

import java.util.ArrayList;
import java.util.Enumeration;

import com.badlogic.gdx.graphics.Texture;

import effet.*;
import entity.Entity;
import entity.Structure;
import entity.Textures;
import entity.Unit;
import map.Map;
import map.Tile;
import math.MATH;
import message.*;
import network.Network;

public class Game {
	
	private static ArrayList<Player> players;
	
	private static ArrayList<Effect> effects;
	private static ArrayList<Effect> effectsToRemove;
	
	private long lastTime;
	
	private static Network network;
	
	private static float[] offset;
	
	private Map map;
	
	
	public Game () {
		Textures.initialize();
		
		players = new ArrayList<Player>();
		//players.add(new Player());
		
		effects = new ArrayList<Effect>();
		effectsToRemove = new ArrayList<Effect>();
		
		network = new Network();
		
		offset = new float[] {0,0};
		
		map = new Map();
				
		lastTime = System.currentTimeMillis();
	}
	
	public boolean inGame() {
		return network.isConnected();
	}
	
	
	public static boolean connectServer(String iPAddress) {
		return network.connect(iPAddress);
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
		
		/*for( Structure structure : getAllstructures()) {
			structure.update(dt);
		}
		
		for( Unit unit : getAllUnits()) {
			unit.update(dt);
		}*/
		
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
	
	/*public static void applyDamage(float posX, float posY, int radius, int amount, int playerExluded) {
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
		
	}*/

	/*public static void createTank(String name, int posX, int posY) {
		players.get(0).addTank(name, posX, posY);
	}
	
	public static void createWorker(String name, int posX, int posY) {
		players.get(0).addWorker(name, posX, posY);
	}

	public static void createFactory(String name, int posX, int posY) {
		players.get(0).addFactory(name, posX, posY);
	}*/

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
			players.get(message.getNumPlayer()).unitSetPos(message.getNameUnit(), message.getPosX() + offset[0], message.getPosY() + offset[1]);
		}else if(messageType.equals("RotMessage")) {
			RotMessage message = (RotMessage) messageReceived;
			players.get(message.getNumPlayer()).unitSetRotation(message.getNameUnit(), message.getRotation(), message.getIdPart());
		}else if(messageType.equals("CreateEntityMessage")) {
			CreateEntityMessage message = (CreateEntityMessage) messageReceived;
			if(message.getTypeEntity().equals("worker")) {
				players.get(message.getNumPlayer()).addWorker(message.getNameEntity(), message.getPosX() + 224 + offset[0], message.getPosY() + offset[1]);
			}else if(message.getTypeEntity().equals("tank")) {
				players.get(message.getNumPlayer()).addTank(message.getNameEntity(), message.getPosX() + 224 + offset[0], message.getPosY() + offset[1]);
			}else if(message.getTypeEntity().equals("headquarter")) {
				players.get(message.getNumPlayer()).addHeadquarter(message.getNameEntity(), message.getPosX() + 224 + offset[0], message.getPosY() + offset[1]);
			}else if(message.getTypeEntity().equals("factory")) {
				players.get(message.getNumPlayer()).addFactory(message.getNameEntity(), message.getPosX() + 224 + offset[0], message.getPosY() + offset[1]);
			}
		}else if(messageType.equals("DestroyEntityMessage")) {
			DestroyEntityMessage message = (DestroyEntityMessage) messageReceived;
			players.get(message.getNumPlayer()).destroyEntity(message.getTypeEntity(), message.getNameEntity());
		}else if(messageType.equals("FireMessage")) {
			FireMessage message = (FireMessage) messageReceived;
			players.get(message.getNumPlayer()).fireUnit(message.getNameUnit());
			createEffect(new TankImpact(message.getImpactPosX() + 224 + offset[0], message.getImpactPosY() + offset[1], 0));
		}else if(messageType.equals("UpdateMoneyMessage")) {
			UpdateMoneyMessage message = (UpdateMoneyMessage) messageReceived;
			players.get(message.getNumPlayer()).updateMoney(message.getMoney());
		}
	}
	
	public Map getMap() {
		return map;
	}

	public void moveCam(float[] camMove) {
		camMove = new float[] {camMove[0]*64, camMove[1]*64};
		offset[0] -= camMove[0];
		offset[1] -= camMove[1];
		
		for(Tile tile : map.getTile()) {
			tile.setPos(tile.getPos()[0] - camMove[0], tile.getPos()[1] - camMove[1]);
		}
		
		for( Structure structure : getAllstructures()) {
			structure.setPos(structure.getPos()[0] - camMove[0], structure.getPos()[1] - camMove[1]);
		}
		
		for( Unit unit : getAllUnits()) {
			unit.setPos(unit.getPos()[0] - camMove[0], unit.getPos()[1] - camMove[1]);
		}
	}
}
