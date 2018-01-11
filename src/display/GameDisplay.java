package display;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import main.Game;
import main.PromptStrike;

public class GameDisplay implements Screen{
	
	private Game game;
	//
	
	private GameScreen gameScreen;
	private InputScreen inputScreen;
	//private OutputScreen outputScreen;
	
	private SpriteBatch renderer;
	
	public GameDisplay (Game game) {
		this.game = game;
		
		gameScreen = new GameScreen(game);
		inputScreen = new InputScreen();
		
		renderer = new SpriteBatch();
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0,0,0,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		renderer.begin();
		gameScreen.render(renderer);
		renderer.end();
		inputScreen.render();
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
