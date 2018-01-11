package entity;


public abstract class Unit extends Entity{
	
	protected int waitMoveDistance;

	public abstract void move(int distance);

	public void move(int x, int y) {
		
		float[] vectDir = {x, y};
		float[] vectRot = {(float) Math.cos(Math.toRadians(getRotation())), (float) Math.sin(Math.toRadians(getRotation()))};
		
		float angle = (float) Math.toDegrees(Math.acos(scalaire(vectDir, vectRot) / (norme(vectDir))));
		
		if(det(vectRot, vectDir) < 0) {
			angle = -angle;
		}
		
		rotate((int) angle);
		//NON TERMINE
		
		waitMoveDistance = (int) (norme(vectDir));
		
	}

	public abstract void rotate(int distance);
	
	protected abstract float getRotation();
	
	private float scalaire (float[] vect1, float[] vect2) {
		return vect1[0]*vect2[0] + vect1[1]*vect2[1];
	}
	
	private double norme(float vect[]) {
		return Math.sqrt(vect[0]*vect[0] + vect[1]*vect[1]);
	}
	
	private float det(float[] vectDir, float[] vectRot) {
		return vectDir[0] * vectRot[1] - vectDir[1] * vectRot[0];
	}
	
}
