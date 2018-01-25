package display;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

import main.Command;

public class InputScreen{
	
	private Skin uiSkin;
	
	private Stage stage;
	private TextField input;
	private List<Label> commandsDisplay;
	
	private static String lastCommand;
	
	public InputScreen () {
		uiSkin = new Skin(Gdx.files.internal("uiskin/uiskin.json"));
		
		input = new TextField("", uiSkin);
		input.setSize(224, 30);
		input.setPosition(224 + 64*13, 0);

		commandsDisplay = new ArrayList<Label>();
		
		stage = new Stage();
		stage.addActor(input);
		stage.setKeyboardFocus(input);
		
		Gdx.input.setInputProcessor(stage);
		
		
	}
	
	public void render () {
		stage.draw();
		
		if (Gdx.input.isKeyJustPressed(Keys.ENTER)){
			newCommand();
		}
		
		if (Gdx.input.isKeyJustPressed(Keys.UP)){
			input.setText(lastCommand);
		}
	}
	
	private void newCommand() {
		String commandText = input.getText();
		input.setText("");
		
		Command.processCommand(commandText);
		
		lastCommand = commandText;
	}
	
	public void dispCommand(String commandText, boolean correct) {
		Label commandDisplay = new Label(commandText, uiSkin);
		
		if(correct) {
			commandDisplay.setColor(Color.WHITE);
		}else {
			commandDisplay.setColor(Color.RED);
		}
		commandDisplay.setPosition(224 + 64*13, input.getHeight());
		
		for (Label label : commandsDisplay) {
			label.setY(label.getY() + label.getHeight());
		}
		
		commandsDisplay.add(commandDisplay);
		stage.addActor(commandDisplay);
		
		lastCommand = commandText;
	}

}
