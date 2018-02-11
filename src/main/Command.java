package main;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;

import display.InputScreen;

public class Command {
	
	private static Game game;
	private static InputScreen inputScreen;

	
	public Command (Game game, InputScreen inputScreen) {
		Command.game = game;
		Command.inputScreen = inputScreen;
	}

	public static void processCommand(String commandText) {
		ArrayList<String> words = new ArrayList<String>();
		for (String word : commandText.split(" ")) {
			words.add(word);
		}
		
		if(isLocalCommand(words.get(0))) {
			processLocalCommand(commandText);
		}else if(game.inGame()){
			Game.sendCommand(commandText);
		}
			
	}
	
	private static boolean isLocalCommand(String firstWord) {
		return firstWord.equals("connect") || firstWord.equals("movecam") || firstWord.equals("mc");
	}

	private static void processLocalCommand(String commandText) {
		boolean commandCorrect = false;
		
		ArrayList<String> words = new ArrayList<String>();
		for (String word : commandText.split(" ")) {
			words.add(word);
		}
		
		String firstWord = words.remove(0);
		
		if(firstWord.equals("connect")) {
			commandCorrect = networkCommand(firstWord, words);
		}else if(firstWord.equals("movecam") || firstWord.equals("mc")) {
			commandCorrect = moveCamCommand(firstWord, words);
		}
		if (commandCorrect) {
			inputScreen.dispCommand(commandText, true);
		}else{
			inputScreen.dispCommand(commandText, false);
		}	
	}

	/*private static boolean networkCommand(String firstWord, ArrayList<String> words) {
		if (words.size() != 1) {
			return false;
		}
		
		String IPAddress = words.get(0);
		
		if (firstWord.equals("connect")) {
			return connectedServer(IPAddress);
		}
		
		return false;
	}*/
	
	private static boolean networkCommand(String firstWord, ArrayList<String> words) {
		if (words.size() != 0) {
			return false;
		}
		
		String IPAddress = "127.0.0.1";
		
		if (firstWord.equals("connect")) {
			return connectedServer(IPAddress);
		}
	
		return false;
	}
	
	private static boolean connectedServer(String IPAddress) {
		return Game.connectServer(IPAddress);
	}
	
	private static boolean moveCamCommand(String firstWord, ArrayList<String> words) {
		if (words.size() != 2) {
			return false;
		}
		
		float[] camMove = new float[2];
		try {
			camMove[0] = Integer.parseInt(words.get(0));
			camMove[1] = Integer.parseInt(words.get(1));
		} catch(NumberFormatException e) {
	        return false; 
	    }
		
		game.moveCam(camMove);
		
		return true;
	}

	/*public static void processServerCommand(int numPlayer, String commandText, boolean correct) {
		if(correct) {
			ArrayList<String> words = new ArrayList<String>();
			for (String word : commandText.split(" ")) {
				words.add(word);
			}
			
			String firstWord = words.remove(0);
			
			if(firstWord.equals("create")) {
				addCommand(numPlayer, words);
			}else if(firstWord.equals("player")) {
				Game.getPlayers().add(new Player());
			}
		}
		inputScreen.dispCommand(commandText, correct);
	}*/
	
	/*private static void addCommand(int numPlayer, ArrayList<String> words) {
		String entityType = words.get(0);
		String entityName = words.get(1);
		int entityPosX = Integer.parseInt(words.get(2));
		int entityPosY = Integer.parseInt(words.get(3));
		
		if(entityType.equals("worker")) {
			Game.createWorker(entityName, entityPosX, entityPosY);
		}else if(entityType.equals("tank")) {
			Game.createTank(entityName, entityPosX, entityPosY);
		}else if(entityType.equals("factory")) {
			Game.createFactory(entityName, entityPosX, entityPosY);
		}
		
	}*/
}
