package entity;

import math.MATH;

public abstract class Unit extends Entity{
	
	public Unit(String name) {
		super(name);
	}

	protected int waitMoveDistance;

	public abstract void move(int distance);

	public void move(int x, int y) {
		
		float[] vectDir = {x, y};
		float[] vectRot = {(float) Math.cos(Math.toRadians(getRotation())), (float) Math.sin(Math.toRadians(getRotation()))};
		
		float angle = (float) Math.toDegrees(Math.acos(MATH.scalaire(vectDir, vectRot) / (MATH.norme(vectDir))));
		
		if(MATH.det(vectRot, vectDir) < 0) {
			angle = -angle;
		}
		
		rotate((int) angle);
		//NON TERMINE
		
		waitMoveDistance = (int) (MATH.norme(vectDir));
		
	}

	public abstract void rotate(int distance);
	
	protected abstract float getRotation();
	
}
