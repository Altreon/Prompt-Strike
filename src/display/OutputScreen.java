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

public class OutputScreen{
	
	private Skin uiSkin;
	
	private Stage stage;
	private static Label moneyDisp;
	private List<Label> commandsDisplay;
	
	public OutputScreen () {
		uiSkin = new Skin(Gdx.files.internal("uiskin/uiskin.json"));

		commandsDisplay = new ArrayList<Label>();
		
		moneyDisp = new Label("MONEY : 0", uiSkin);
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
