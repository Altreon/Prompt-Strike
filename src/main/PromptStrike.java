package main;


import com.badlogic.gdx.Game;

import display.GameDisplay;
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
//		Gdx.gl.glClearColor(1, 0, 0, 1);
//		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//		batch.begin();
//		batch.draw(img, 0, 0);
//		batch.end();
		super.render();
		game.update();
	}
	
	@Override
	public void dispose () {
	
	}
}
