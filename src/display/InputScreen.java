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

import assets.FontFiles;
import assets.Fonts;
import command.Command;
import map.Map;

public class InputScreen{
	
	private Skin uiFont; // Store the character font to display command from a file
	
	private Stage stage; // UI group, usually use to do different actions on group and show different parts of HUD in different way.
	private TextField input; // The command input
	
	private List<Label> commandsDisplay;
	
	private static ArrayList<String> commandsHistory;
	private static int history;
	
	
	public InputScreen () {
		uiFont = Fonts.getFont(FontFiles.DigitalFont);
		
		input = new TextField("", uiFont);
		input.setSize(Map.getOffsetFromEdge(), 30);
		input.setPosition(Map.getOffsetFromEdge() + Map.getTileSize()*Map.getMapSizeX(), 0);

		commandsDisplay = new ArrayList<Label>();
		
		commandsHistory = new ArrayList<String>();
		
		stage = new Stage();
		stage.addActor(input);
		stage.setKeyboardFocus(input);
		
		Gdx.input.setInputProcessor(stage);
		
		
	}
	
	public void render () {
		stage.draw();
		
		//Send a command
		if (Gdx.input.isKeyJustPressed(Keys.ENTER)){
			newCommand();
			history = 0;
		}
		
		//rewrite a previous command
		if (Gdx.input.isKeyJustPressed(Keys.UP) && history < commandsHistory.size()){
			history++;
			input.setText(commandsHistory.get(commandsHistory.size() - history));
		}
		
		//rewrite a next command
		if (Gdx.input.isKeyJustPressed(Keys.DOWN) && history > 1){
			history--;
			input.setText(commandsHistory.get(commandsHistory.size() - history));
		}		
	}
	
	private void newCommand() {
		String commandText = input.getText();
		input.setText("");
		
		Command.processCommand(commandText);
		
		//commandsHistory.add(commandText); ???
	}
	
	public void dispCommand(String commandText, boolean correct) {
		Label commandDisplay = new Label(commandText, uiFont);
		
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
		
		commandsHistory.add(commandText);
	}

}
