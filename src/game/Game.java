package game;

import java.util.ArrayList;

import java.util.Enumeration;

import Player.Player;
import assets.Textures;
import effet.*;
import entity.Structure;
import entity.Unit;
import launcher.PromptStrike;
import map.Map;
import map.Tile;
import message.*;
import network.Network;

public class Game {
	
	private static ArrayList<Player> players;
	
	private static ArrayList<Effect> effects;
	private static ArrayList<Effect> effectsToRemove; //Used to remove effects on the game thread, and avoid some bugs
	
	private Map map;
	
	private long lastTime; //Used to calculate the delta time between two loop
	
	private static Network network; //The network manager
	
	private static float[] offset; //Used to store the position of "view" or "camera" on the map
	
	
	public Game () {
		Textures.initialize(); //Load all textures files on RAM 
		
		players = new ArrayList<Player>();
		
		effects = new ArrayList<Effect>();
		effectsToRemove = new ArrayList<Effect>();
		
		network = new Network();
		
		offset = new float[] {Map.getOffsetFromEdge(),0};
		
		map = new Map();
				
		lastTime = System.currentTimeMillis();
	}
	
	//if connected to the server
	public boolean inGame() {
		return network.isConnected();
	}
	
	//try to connect to a server
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
	
	public Map getMap() {
		return map;
	}

	public void update() { // Game loop
		long currentTime = System.currentTimeMillis();
		int dt = (int) (System.currentTimeMillis() - lastTime);
		
		for (Effect effect : effects) {
			effect.timeDecrease(dt);
		}
		
		RemoveDestroyedEffects();
		
		lastTime = currentTime;
	}
	
	private void RemoveDestroyedEffects () {

		for (Effect effect : effectsToRemove) {
			effects.remove(effect);
		}
		
		effectsToRemove.clear();
	}

	public static void processMessage(Message messageReceived) {
		String messageType = messageReceived.getClass().getSimpleName();
		
		//each type of message execute a simple action with data in its attributes
		
		//Maybe the most unreadable function of this application...
		
		if(messageType.equals("CommandMessage")) {
			CommandMessage message = (CommandMessage) messageReceived;
			PromptStrike.getInputScreen().dispCommand(message.getCommand(), message.getCorrect());
			
		}else if(messageType.equals("NewPlayerMessage")) {
			NewPlayerMessage message = (NewPlayerMessage) messageReceived;
			//Currently, the "NewPlayerMessage" only serves to add a other player on the party
			players.add(new Player());
			
		}else if(messageType.equals("PosMessage")) {
			PosMessage message = (PosMessage) messageReceived;
			players.get(message.getNumPlayer()).unitSetPos(message.getNameUnit(), message.getPosX() + offset[0], message.getPosY() + offset[1]);
			
		}else if(messageType.equals("RotMessage")) {
			RotMessage message = (RotMessage) messageReceived;
			players.get(message.getNumPlayer()).unitSetRotation(message.getNameUnit(), (float) Math.toDegrees(message.getRotation()), message.getIdPart());
			
		}else if(messageType.equals("CreateEntityMessage")) {
			CreateEntityMessage message = (CreateEntityMessage) messageReceived;
			
			if(message.getTypeEntity().equals("worker")) {
				players.get(message.getNumPlayer()).addWorker(message.getNameEntity(), message.getPosX() + offset[0], message.getPosY() + offset[1]);
				
			}else if(message.getTypeEntity().equals("tank")) {
				players.get(message.getNumPlayer()).addTank(message.getNameEntity(), message.getPosX() + offset[0], message.getPosY() + offset[1]);
				
			}else if(message.getTypeEntity().equals("headquarter")) {
				players.get(message.getNumPlayer()).addHeadquarter(message.getNameEntity(), message.getPosX() + offset[0], message.getPosY() + offset[1]);
				
			}else if(message.getTypeEntity().equals("factory")) {
				players.get(message.getNumPlayer()).addFactory(message.getNameEntity(), message.getPosX() + offset[0], message.getPosY() + offset[1]);
				
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
			
		}else if(messageType.equals("EndGameMessage")) {
			EndGameMessage message = (EndGameMessage) messageReceived;
			
			if(message.areYouWin()) {
				PromptStrike.getInputScreen().dispCommand("YOU WIN", true);
			}else {
				PromptStrike.getInputScreen().dispCommand("YOU LOSE", false);
			}
			PromptStrike.getInputScreen().dispCommand("wait the end of the game...", true);
		}
	}

	public void moveCam(float[] camMove) {
		camMove = new float[] {camMove[0]*Map.getTileSize(), camMove[1]*Map.getTileSize()}; //adapt to size of tiles
		offset[0] -= camMove[0];
		offset[1] -= camMove[1];
		
		
		//move all elements on the screen
		
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

	//Clear static list and return to the game menu (Currently a black screen)
	public static void disconnect() {
		players.clear();
		effects.clear();
		network.disconnect();
		
		System.gc(); //Runs the garbage collector. that destroy unused variable to alleviate the RAM
	}
}
