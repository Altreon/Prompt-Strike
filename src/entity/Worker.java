package entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

import main.Game;
import map.Map;

public class Worker extends Unit{

	private Part sprite;
	
	private final int FACTORY = 0;
	
	private String action;
	private int actionTimeRemaining;
	
	private final int[] buildTime = {100}; // à augmenter
	private final int[] buildCost = {10};
	
	private int buildType;
	private String buildName;
	
	private final int GATHERTIME = 1000;
	
	public Worker (int posX, int posY) {
		posX = posX * 64 + 224;
		posY *= 64;
		
		sprite = new Part(new Texture("Units/worker.png"));
		sprite.setPosition(posX, posY);
		
		sprite.SPEEDMOVE = 1;
		sprite.SPEEDROTATE = 1;
		
		action = "";
	}
	
	@Override
	public void render(Batch batch) {
		sprite.draw(batch);
		
	}

	@Override
	public void update(int dt) {
		if(action.equals("build")) {
			
			if(actionTimeRemaining > 0) {
				actionTimeRemaining -= dt;
			}else {
				actionTimeRemaining = 0;
				if(buildType == FACTORY) {
					Game.createFactory(buildName, (int)(sprite.getX()/64 - 224/64), (int)(sprite.getY()/64));
				}else {
					//rien pour le moment
				}
				action = "";
			}
		
		}else if(action.equals("gather")){
			if(actionTimeRemaining > 0) {
				actionTimeRemaining -= dt;
			}else {
				actionTimeRemaining = 0;
				System.out.println("j'ai récolté un truc!");
				gather();
			}
		}else {
		
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
		action = "";
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
		action = "";
	}

	@Override
	protected float getRotation() {
		return sprite.getRotation();
	}

	public boolean canBuild(String structure) {
		return structure.equals("factory");
	}
	
	public void build (String structure, String structName) {
		if(structure.equals("factory")) {
			buildType = FACTORY;
		}else {
			//rien pour le moment
		}
		
		buildName = structName;
		actionTimeRemaining = buildTime[buildType];
		
		action = "build";
	}
	
	public boolean canGather () {
		return Map.getTileType( (int)(sprite.getX()/64 - 224/64), (int)(sprite.getY()/64)).equals("grass");
	}
	
	public void gather () {
		action = "gather";
		actionTimeRemaining = GATHERTIME;
	}

}
