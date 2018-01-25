package entity;

import math.MATH;

public abstract class Unit extends Entity{
	
	public Unit(String name) {
		super(name);
	}

	public abstract void setRotation(float rotation);
	
	protected abstract float getRotation();
	
}
