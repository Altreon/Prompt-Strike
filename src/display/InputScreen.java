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
	}
	
	private void newCommand() {
		String commandText = input.getText();
		input.setText("");
		
		Color ColorCommand =  Command.processCommand(commandText);
				
		Label commandDisplay = new Label(commandText, uiSkin);
		commandDisplay.setColor(ColorCommand);
		commandDisplay.setPosition(224 + 64*13, input.getHeight());
		
		for (Label label : commandsDisplay) {
			label.setY(label.getY() + label.getHeight());
		}
		
		commandsDisplay.add(commandDisplay);
		stage.addActor(commandDisplay);
	}

}
