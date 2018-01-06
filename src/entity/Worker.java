package entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class Worker extends Unit{

	private Part sprite;
	
	public Worker (int posX, int posY) {
		posX = posX * 64 + 224;
		posY *= 64;
		
		sprite = new Part(new Texture("Units/worker.png"));
		sprite.setPosition(posX, posY);
		
		sprite.SPEEDMOVE = 1;
		sprite.SPEEDROTATE = 1;
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
	public void moveToPoint(int x, int y) {
		float posX = sprite.getX() + x * 64;
		float posY = sprite.getY() + y * 64;
		
		float angle = (float) Math.acos(scalaire(sprite.getX(), posX, sprite.getY(), posY));
		
		rotate((int) Math.toDegrees(angle));
		//NON TERMINE
		
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
	
	private float scalaire (float x1, float x2, float y1, float y2) {
		return x1*x2 + y1*y2;
	}

}
