/**
 * 
 * @author zackh_000
 * @version 1.0
 */

public class ParticleManager {

	private double deltaT;
	private double time;
	private Star star;
	private RungeKuttaSolver solver;

	public ParticleManager(double timeInterval, Star fireworkStar, RungeKuttaSolver rKSolver) throws IllegalTimeIntervalException {
		if(timeInterval <= 0 || timeInterval > 0.05) {
			throw new IllegalTimeIntervalException("The supplied time interval was not" +
					"greater than 0 and less than or equal to 0.05 seconds.");
		}
		setDeltaT(timeInterval);
		setTime();
		setStar(fireworkStar);
		setSolver(rKSolver);
	} // end full constructor

	public void setDeltaT (double interval) {
		deltaT = interval;
	}

	private void setTime() {
		time = 0;
	}
	
	private void setStar(Star fireworkStar) {
		star = fireworkStar;
	}
	
	private void setSolver(RungeKuttaSolver rKSolver) {
		solver = rKSolver;
	}

	private double getDeltaT() {
		return deltaT;
	}

	private double getTime() {
		return time;
	}
	
	private Star getStar() {
		return star;
	}
	
	private RungeKuttaSolver getSolver() {
		return solver;
	}

	public double[][] getPath (double vxInitial, double vyInitial, double vWind) {
		int numPoints = (int)Math.round(getStar().getInitialMass() / getStar().getBurnRate() / getDeltaT() ) + 1;
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
			newVals = getSolver().solve(getTime(), getDeltaT());
			vx = newVals[0];
			vy = newVals[1];
			time = time + getDeltaT();
			points[i][0] = time;
			// Calculation the positions
			points[i][1] = points[i - 1][1] + vx * getDeltaT();
			points[i][2] = points[i - 1][2] + vy * getDeltaT();
		}
		return points;
	} // end getPath

} // end ParticleManager class
