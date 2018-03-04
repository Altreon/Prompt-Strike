package display;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import assets.Fonts;
import game.Game;

public class Display implements Screen{
	
	private Game game;
	
	private GameScreen gameScreen;
	private InputScreen inputScreen;
	private OutputScreen outputScreen;
	
	private SpriteBatch renderer;
	
	public Display (Game game) {
		this.game = game;
		
		Fonts.initialize();
		
		gameScreen = new GameScreen(game);
		inputScreen = new InputScreen();
		outputScreen = new OutputScreen();
		
		renderer = new SpriteBatch();
		
	}

	@Override
	public void show() {
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0,0,0,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		if(game.inGame()) {
			renderer.begin();
			gameScreen.render(renderer);
			renderer.end();
		}
		inputScreen.render();
		outputScreen.render();
		
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

	public InputScreen getInputScreen() {
		return inputScreen;
	}

}
