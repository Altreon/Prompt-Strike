package main;

//Sécurité nommage, il est possible que 2 unité de nom différent soit créer dans 2 factory différentes.


import com.badlogic.gdx.Game;

import display.GameDisplay;
import display.InputScreen;
public class PromptStrike extends Game {
	
	private main.Game game;
	
	private static GameDisplay gameDisplay;
	// private InputWindows
	// private OutputWindows
	
	private Command commande;
	
	@Override
	public void create () {
		game = new main.Game();
		
		gameDisplay = new GameDisplay(game);
		super.setScreen(gameDisplay);
		
		commande = new Command(game, gameDisplay.getInputScreen());
	}

	@Override
	public void render () {
		super.render();
		game.update();
	}
	
	@Override
	public void dispose () {
	
	}
	
	public static InputScreen getInputScreen() {
		return gameDisplay.getInputScreen();
	}
}
