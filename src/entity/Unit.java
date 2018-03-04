package entity;

import com.badlogic.gdx.graphics.g2d.Batch;

public abstract class Unit extends Entity{
	private final int MAINPART = 0;
	protected Part[] parts;
	
	public Unit(String name, float posX, float posY, int nbPart) {
		super(name, posX, posY);
		
		//A parts variable need to be initialized non-null for the render function
		parts = new Part[nbPart];
	}
	
	public void render(Batch batch) {
		for (Part part : parts) {
			if(part != null) { //To avoid bug when a render loop took place before parts initialization (different threads)
				part.draw(batch);
			}
		}
	}

	public void setPos(float posX, float posY) {
		super.setPos(posX, posY);
		parts[MAINPART].setPosition(posX, posY);
	}
	
	public void setRotation(float rotation, int idPart) {
		parts[idPart].setRotation(rotation);
	}

	public float getRotation() {
		return parts[MAINPART].getRotation();
	}
	
}
