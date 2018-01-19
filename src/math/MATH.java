package math;

public final class MATH {
	
	public static float scalaire (float[] vect1, float[] vect2) {
		return vect1[0]*vect2[0] + vect1[1]*vect2[1];
	}
	
	public static double norme(float vect[]) {
		return Math.sqrt(vect[0]*vect[0] + vect[1]*vect[1]);
	}
	
	public static float det(float[] vectDir, float[] vectRot) {
		return vectDir[0] * vectRot[1] - vectDir[1] * vectRot[0];
	}
	
	public static double dist(float[] point1, float[] point2) {
		return Math.sqrt(Math.pow(point2[0]-point1[0], 2) + Math.pow(point2[1]-point1[1], 2));
	}
}
