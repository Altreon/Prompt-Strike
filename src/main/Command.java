package main;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;

import display.InputScreen;

public class Command {
	
	private Game game;
	private static InputScreen inputScreen;

	
	public Command (Game game, InputScreen inputScreen) {
		this.game = game;
		this.inputScreen = inputScreen;
	}

	public static void processCommand(String commandText) {
		if(!Game.inGame()) {
			processLocalCommand(commandText);
		}else {
			Game.sendCommand(commandText);	
		}
			
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
		}
		if (commandCorrect) {
			inputScreen.dispCommand(commandText, true);
		}else{
			inputScreen.dispCommand(commandText, false);
		}
			
	}

	private static boolean networkCommand(String firstWord, ArrayList<String> words) {
		if (words.size() != 0) {
			return false;
		}
		
		if (firstWord.equals("connect")) {
			return connectedServer(words);
		}
		
		return false;
	}
	
	private static boolean connectedServer(ArrayList<String> words) {
		Game.connectServer();
		return true;
	}

	public static void processServerCommand(int numPlayer, String commandText, boolean correct) {
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
	}
	
	private static void addCommand(int numPlayer, ArrayList<String> words) {
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
		
	}
}
