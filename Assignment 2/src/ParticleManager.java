/**
 * 
 * @author zackh_000
 * @version 1.0
 */

public class ParticleManager {
	
	private double deltaT;
	private ODESystem star;
	
	public ParticleManager(double timeInterval, ODESystem star) throws IllegalTimeIntervalException {
		setDeltaT(timeInterval);
	} // end full constructor
	
	private void setDeltaT (double interval) {
		deltaT = interval;
	}
	
	private double getDeltaT() {
		return deltaT;
	}

	public static double[][] getPath (double vxInitial, double vyInitial, double vWind) {
		int numPoints = (int)Math.round(star.getInitialMass() / star.getBurnRate() / getDeltaT() ) + 1;
		double[][] points = new double[numPoints][3];
		double[] newVals = new double[2];
		double vx, vy;
		double time = 0;
		// Assign the time=0 point
		points[0][0] = time;
		points[0][1] = 0;
		points[0][2] = 0;
		// Calculate the rest of the points
		vx = vxInitial;
		vy = vyInitial;
		for (int i = 1; i < numPoints; i++) {
			newVals = estimateVelocity(time, vx, vy, vWind);
			vx = newVals[0];
			vy = newVals[1];
			time = time + DELTA_T;
			points[i][0] = time;
			// Calculation the positions
			points[i][1] = points[i - 1][1] + vx * DELTA_T;
			points[i][2] = points[i - 1][2] + vy * DELTA_T;
		}
		return points;
	} // end getPath
	
} // end ParticleManager class
