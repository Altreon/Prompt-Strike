package command;

import java.util.ArrayList;

import display.InputScreen;
import game.Game;

public class Command {
	
	private static Game game;
	private static InputScreen inputScreen;

	/*
	 * Here, the process of treat command is simple,
	 * if the entered command is local (like connect to server), the command system work like the server,
	 * the words of the command are decomposing and analyzing to do the correct action with the asked values.
	 * Else, the command string is sending to the server.
	 */
	
	public Command (Game game, InputScreen inputScreen) {
		Command.game = game;
		Command.inputScreen = inputScreen;
	}

	public static void processCommand(String commandText) {
		ArrayList<String> words = new ArrayList<String>();
		for (String word : commandText.split(" ")) {
			words.add(word);
		}
		
		if(isLocalCommand(words.get(0)) || !game.inGame()) {
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
		
		//gets a list of command's words
		ArrayList<String> words = new ArrayList<String>();
		for (String word : commandText.split(" ")) {
			words.add(word);
		}
		
		String firstWord = words.remove(0);
		
		if(firstWord.equals("connect")) {
			commandCorrect = networkCommand(firstWord, words);
		}else if(firstWord.equals("movecam") || firstWord.equals("mc")) {
			commandCorrect = moveCamCommand(words);
		}
		if (commandCorrect) {
			inputScreen.dispCommand(commandText, true);
		}else{
			inputScreen.dispCommand(commandText, false);
		}	
	}
	
	private static boolean networkCommand(String firstWord, ArrayList<String> words) {
		if (words.size() != 1) {
			return false;
		}
		
		String IPAddress = words.get(0);
		
		if (firstWord.equals("connect")) {
			return connectedServer(IPAddress);
		}
	
		return false;
	}
	
	private static boolean connectedServer(String IPAddress) {
		return Game.connectServer(IPAddress);
	}
	
	private static boolean moveCamCommand(ArrayList<String> words) {
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
}
