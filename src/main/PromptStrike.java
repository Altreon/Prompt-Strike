package main;

//Sécurité nommage, il est possible que 2 unité de nom différent soit créer dans 2 factory différentes.


import com.badlogic.gdx.Game;

import display.GameDisplay;
import network.Client;
import network.Server;
public class PromptStrike extends Game {
	
	private main.Game game;
	
	private GameDisplay gameDisplay;
	// private InputWindows
	// private OutputWindows
	
	@Override
	public void create () {
		game = new main.Game();
		
		gameDisplay = new GameDisplay(game);
		super.setScreen(gameDisplay);
	}

	@Override
	public void render () {
		super.render();
		game.update();
	}
	
	@Override
	public void dispose () {
	
	}
}
