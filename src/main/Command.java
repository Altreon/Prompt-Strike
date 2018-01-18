package main;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;

public class Command {
	
	//garder?
	private static int player = 0; 

	
	public static void loadWord () {
		//word.add
	}

	public static Color processCommand(String commandText) {
		boolean commandCorrect = false;
		
		
		ArrayList<String> words = new ArrayList<String>();
		for (String word : commandText.split(" ")) {
			words.add(word);
		}
		
		String firstWord = words.remove(0);
		
		if (Game.getPlayers().get(player).isUnit(firstWord)) {
			commandCorrect = unitCommand(firstWord, words);
		}
		
		if (Game.getPlayers().get(player).isStructure(firstWord)) {
			commandCorrect = structCommand(firstWord, words);
		}else {
			commandCorrect = networkCommand(firstWord, words);
		}
		
		if (commandCorrect) {
			return Color.WHITE;
		}else{
			return Color.RED;
		}
			
	}
	
	private static boolean networkCommand(String firstWord, ArrayList<String> words) {
		if (words.size() != 0) {
			return false;
		}
		
		if (firstWord.equals("create")) {
			return createServer(words);
		}
		
		return false;
	}

	private static boolean createServer(ArrayList<String> words) {
		Game.createServer();
		return true;
	}

	private static boolean unitCommand(String unitName, ArrayList<String> words) {
		if (words.size() == 0) {
			return false;
		}
		
		String nextWord = words.remove(0);
		
		if (nextWord.equals("move") || nextWord.equals("m")) {
			return move(unitName, words);
		}
		
		if (nextWord.equals("rotate") || nextWord.equals("r")) {
			return rotate(unitName, words);
		}
		
		if (nextWord.equals("fire") || nextWord.equals("f")) {
			return fire(unitName, words);
		}
		
		if (nextWord.equals("build") || nextWord.equals("b")) {
			return build(unitName, words);
		}
		
		if (nextWord.equals("gather") || nextWord.equals("g")) {
			return gather(unitName, words);
		}
		
		return false;
	}

	private static boolean move(String unitName, ArrayList<String> words) {
		if (words.size() == 1) {
			return moveDir(unitName, words);
		}else if (words.size() == 2) {
			return movePos(unitName, words);
		}else {
			return false;
		}
	}
	
	private static boolean moveDir(String unitName, ArrayList<String> words) {
		int value = 0;
		try {
			value = Integer.parseInt(words.get(0));
		} catch(NumberFormatException e) {
	        return false; 
	    }
		
		if(value >= -10 && value <= 10) {
			Game.getPlayers().get(player).moveUnit(unitName, value);
			return true;
		}
		
		return false;
	}
	
	private static boolean movePos(String unitName, ArrayList<String> words) {
		int posX = 0;
		int posY = 0;
		try {
			posX = Integer.parseInt(words.get(0));
			posY = Integer.parseInt(words.get(1));
		} catch(NumberFormatException e) {
	        return false; 
	    }
		
		if(posX >= -10 && posX <= 10 && posY >= -10 && posY <= 10) {
			Game.getPlayers().get(player).moveUnit(unitName, posX, posY);
			return true;
		}
		
		return false;
	}
	
	private static boolean rotate(String unitName, ArrayList<String> words) {
		if (words.size() == 0) {
			return false;
		}
		
		String nextWord = words.remove(0);
		if(nextWord.equals("cannon") || nextWord.equals("c")) {
			return rotateCannon(unitName, words);
		}else {
			return rotateUnit(unitName, nextWord, words);
		}
	}
	
	private static boolean rotateUnit(String unitName, String sValue, ArrayList<String> words) {
		if (words.size() != 0) {
			return false;
		}
		
		int value = 0;
		try {
			value = Integer.parseInt(sValue);
		} catch(NumberFormatException e) { 
	        return false; 
	    }
		
		if(value >= -360 && value <= 360) {
			Game.getPlayers().get(player).rotateUnit(unitName, value);
			return true;
		}
		
		return false;
	}
	
	private static boolean rotateCannon(String unitName, ArrayList<String> words) {
		if (words.size() != 1 || !Game.getPlayers().get(player).unitCanRotateCannon(unitName)) {
			return false;
		}
		
		int value = 0;
		try {
			value = Integer.parseInt(words.get(0));
		} catch(NumberFormatException e) { 
	        return false; 
	    }
		
		if(value >= -360 && value <= 360) {
			Game.getPlayers().get(player).rotateCannon(unitName, value);
			return true;
		}
		
		return false;
	}
	
	private static boolean fire(String unitName, ArrayList<String> words) {
		if (words.size() != 1 || !Game.getPlayers().get(player).unitCanFire(unitName)) {
			return false;
		}
		
		int value = 0;
		try {
			value = Integer.parseInt(words.get(0));
		} catch(NumberFormatException e) { 
	        return false; 
	    }
		
		if(value >= 1 && value <= 5) {
			Game.getPlayers().get(player).fireUnit(unitName, value, player);
			return true;
		}
		
		return false;
	}
	
	private static boolean build(String unitName, ArrayList<String> words) {
		if (words.size() != 2) {
			return false;
		}
		
		String structType = words.get(0);
		String structName = words.get(1);
		
		if(Game.getPlayers().get(player).unitCanBuild(unitName, structType, structName)) {
			Game.getPlayers().get(player).buildUnit(unitName, structType, structName);
			return true;
		}
		
		return false;
	}
	
	private static boolean gather (String unitName, ArrayList<String> words) {
		if (words.size() != 0) {
			return false;
		}
		
		if(Game.getPlayers().get(player).unitCanGather(unitName)) {
			Game.getPlayers().get(player).gatherUnit(unitName);
			return true;
		}
		
		return false;
	}
	
	private static boolean structCommand(String structName, ArrayList<String> words) {
		if (words.size() == 0) {
			return false;
		}
		
		String nextWord = words.remove(0);
		
		if (nextWord.equals("produce") || nextWord.equals("p")) {
			return produce(structName, words);
		}
		
		return false;
	}

	private static boolean produce(String structName, ArrayList<String> words) {
		if (words.size() != 2) {
			return false;
		}
		
		String unitType = words.get(0);
		String unitName = words.get(1);
		
		if(Game.getPlayers().get(player).structCanProduce(structName, unitType, unitName)) {
			Game.getPlayers().get(player).structProduce(structName, unitType, unitName);
			return true;
		}
		
		return false;
	}

}
