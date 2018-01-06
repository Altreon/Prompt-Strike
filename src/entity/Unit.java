package entity;


public abstract class Unit extends Entity{

	public abstract void move(int distance);
	public abstract void moveToPoint(int x, int y);
	public abstract void rotate(int distance);
	
}
