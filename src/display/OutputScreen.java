package display;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import assets.FontFiles;
import assets.Fonts;

public class OutputScreen{
	
	//Globally unused for now
	
	private Skin uiFont; //character font to display data
	
	private Stage stage; // UI group, usually use to do different actions on group and show different parts of HUD in different way.
	private static Label moneyDisp;
	private List<Label> messagesDisplay; //Unused for now, it is for show different message from the server
	
	public OutputScreen () {
		uiFont = Fonts.getFont(FontFiles.DigitalFont);

		messagesDisplay = new ArrayList<Label>();
		
		moneyDisp = new Label("MONEY : 0", uiFont);
		moneyDisp.setPosition(0, 0);
		
		stage = new Stage();
		stage.addActor(moneyDisp);
	}
	
	public void render () {
		stage.draw();
	}
	
	public static void updateMoney(int value) {
		moneyDisp.setText("MONEY : " + Integer.toString(value));
	}

}
