package launcher;

import com.badlogic.gdx.Game;

import command.Command;
import display.Display;
import display.InputScreen;
public class PromptStrike extends Game {
	
	private game.Game game;
	
	private static Display gameDisplay; //The display manager
	
	private Command command;
	
	@Override
	public void create () {
		game = new game.Game();
		
		gameDisplay = new Display(game);
		super.setScreen(gameDisplay);
		
		//only used to add game and inputScreen object at Command class
		command = new Command(game, gameDisplay.getInputScreen());
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
