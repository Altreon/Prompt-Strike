package entity;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

import math.MATH;

public abstract class Unit extends Entity{
	private final int MAINPART = 0;
	protected Part[] parts;
	
	public Unit(String name, float posX, float posY, int nbPart) {
		super(name, posX, posY);
		parts = new Part[nbPart];
	}
	
	public void render(Batch batch) {
		for (Part part : parts) {
			if(part != null) {
				part.draw(batch);
			}
		}
	}

	public void setPos(float posX, float posY) {
		super.setPos(posX, posY);
		parts[MAINPART].setPosition(posX, posY);
	}
	
	//public float[] getPos() {
		//return new float[]{chassis.getX(), chassis.getY()};
	//}
	
	public void setRotation(float rotation, int idPart) {
		parts[idPart].setRotation(rotation);
	}

	public float getRotation() {
		return parts[MAINPART].getRotation();
	}
	
	//public Part[] getParts () {
		//return parts;
	//}
	
}
