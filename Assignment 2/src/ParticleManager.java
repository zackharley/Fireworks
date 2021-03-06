/**
 * A class to store information about the particle manager for a firework star simulation.
 * <p>
 * The time interval, the current time, the star, and the Runge Kutta Solver are stored.
 * </p>
 * @author zackh_000
 * @version 1.0
 */
public class ParticleManager {

	private double deltaT;
	private double time;
	private Star star;
	private RungeKuttaSolver solver;
	
	/**
	 * The constructor for the ParticleManager class.
	 * @param timeInterval The time interval, in seconds, that will be used for the simulation.
	 * @param fireworkStar The Star object that will be used for the simulation.
	 * @param rKSolver The RungeKuttaSolver object that will be used to solve the simulation.
	 * @throws IllegalTimeIntervalException if the timeInteval was not greater than 0 and less
	 * than or equal to 0.05
	 */
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

	/**
	 * The mutator for deltaT.
	 * @param interval The new time interval that will be used in the simulation.
	 */
	public void setDeltaT (double interval) {
		deltaT = interval;
	} // end 

	private void setTime() {
		time = 0;
	} // end 
	
	private void setStar(Star fireworkStar) {
		star = fireworkStar;
	} // end 
	
	private void setSolver(RungeKuttaSolver rKSolver) {
		solver = rKSolver;
	} // end 

	private double getDeltaT() {
		return deltaT;
	} // end 

	private double getTime() {
		return time;
	} // end 
	
	private Star getStar() {
		return star;
	} // end 
	
	private RungeKuttaSolver getSolver() {
		return solver;
	} // end 

	/**
	 * The method used to solve for the x and y position of the star at given different launch velocities.
	 * @param vxInitial The initial x velocity, not taking into account wind velocity.
	 * @param vyInitial The initial y velocity.
	 * @param vWind The wind velocity in the environment around the star.
	 * @return The x and y positions of the star at different times. 
	 */
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
		star.setCurrentValues(points);
		return points;
	} // end getPath

} // end ParticleManager class
