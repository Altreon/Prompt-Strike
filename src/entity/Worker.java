package entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

import main.Game;

public class Worker extends Unit{

	private Part sprite;
	
	private final int FACTORY = 0;
	
	private final int[] buildTime = {1000};
	private final int[] buildCost = {10};
	
	private long buildTimeStart;
	private long buildTimeRemaining;
	private int buildType;
	private String buildName;
	
	public Worker (int posX, int posY) {
		posX = posX * 64 + 224;
		posY *= 64;
		
		sprite = new Part(new Texture("Units/worker.png"));
		sprite.setPosition(posX, posY);
		
		sprite.SPEEDMOVE = 1;
		sprite.SPEEDROTATE = 1;
		
		buildTimeRemaining = -1;
	}
	
	@Override
	public void render(Batch batch) {
		sprite.draw(batch);
		
	}

	@Override
	public void update() {
		if(sprite.isMoving()){
			sprite.updateMove();
		}
		if(sprite.isRotating()){
			sprite.updateRotate();
		}
		
		if(!sprite.isRotating() && waitMoveDistance != 0){
			move(waitMoveDistance);
			waitMoveDistance = 0;
		}
		
		if(buildTimeRemaining >= 0) {
			buildTimeRemaining = System.currentTimeMillis() - buildTimeStart;
			if(buildTimeRemaining >= buildTime[buildType]) {
				if(buildType == FACTORY) {
					Game.createFactory(buildName, (int)(sprite.getX()/64 - 224/64), (int)(sprite.getY()/64));
				}else {
					//rien pour le moment
				}
				buildTimeRemaining = -1;
			}
		}
		
	}

	@Override
	public void move(int distance) {
		if(distance > 0) {
			sprite.moveDistance = distance*64;
			sprite.moveDirection = 1;
		}else {
			sprite.moveDistance = -distance*64;
			sprite.moveDirection = -1;
		}
		
	}

	@Override
	public void rotate(int distance) {
		if(distance > 0) {
			sprite.rotateDistance = distance;
			sprite.rotateDirection = 1;
		}else {
			sprite.rotateDistance = -distance;
			sprite.rotateDirection = -1;
		}
		
	}

	@Override
	protected float getRotation() {
		return sprite.getRotation();
	}

	public boolean canBuild(String structure) {
		return buildTimeRemaining == -1 && (structure.equals("factory"));
	}
	
	public void build (String structure, String structName) {
		if(structure.equals("factory")) {
			buildType = FACTORY;
		}else {
			//rien pour le moment
		}
		
		buildName = structName;
		buildTimeStart = System.currentTimeMillis();
		buildTimeRemaining = buildTime[buildType];
	}

}
