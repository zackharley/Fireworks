/**
 * 
 * @author zackh_000
 * @version 1.0
 */
public class RungeKuttaSolver {

	private Star star;
	private Environment env;
	
	public RungeKuttaSolver(Star fireworkStar, Environment environment) {
		setStar(fireworkStar);
		setEnv(environment);
	} // end full constructor
	
	private void setStar(Star fireworkStar) {
		star = fireworkStar;
	}
	
	private void setEnv(Environment environment) {
		env = environment;
	}

	// This method returns the value of the fx function, given the 
	// time in seconds and the two velocity components in m/sec.
	// The meaning of fx is described in the assignment statement.
	public double xDE(double time, double vxa, double vy) {
		star.setVelocityXA(vxa);
		star.setVelocityY(vy);
		star.setVelocityMag();
		star.setCurrentMass(time);
		star.setVolume();
		star.setRadius();
		star.setArea();
		star.setDragForce(env.getAirDensity(), env.getDragCoeff());
		return -star.getDragForce() * vxa / (star.getCurrentMass() * star.getVelocityMag());
	} // end xDE

	// This method returns the value of the fy function, given the 
	// time in seconds and the two velocity components in m/sec.
	// The meaning of fy is described in the assignment statement.
	public double yDE(double time, double vxa, double vy) {
		star.setVelocityXA(vxa);
		star.setVelocityY(vy);
		star.setVelocityMag();
		star.setCurrentMass(time);
		star.setRadius();
		star.setDragForce(env.getAirDensity(), env.getDragCoeff());
		return -env.getGravity() - star.getDragForce() * vy / (star.getCurrentMass() * star.getVelocityMag());
	} // end yDE

	public double[] solve(double time, double deltaT) {
		double q1x, q2x, q3x, q4x, q1y, q2y, q3y, q4y;
		double halfTime = time + deltaT / 2;
		double fullTime = time + deltaT;
		double[] newVals = new double[2];
		// Use the apparent velocity in the differential equations, so the drag force
		// is calculated using vxa, rather than just vx.
		double vx = star.getVelocityX();
		double vxa = star.getVelocityXA();
		double vy = star.getVelocityY();
		q1x = xDE(time, vxa, vy);
		q1y = yDE(time, vxa, vy);
		q2x = xDE(halfTime, vxa + deltaT * q1x / 2, vy + deltaT * q1y / 2);
		q2y = yDE(halfTime, vxa + deltaT * q1x / 2, vy + deltaT * q1y / 2);
		q3x = xDE(halfTime, vxa + deltaT * q2x / 2, vy + deltaT * q2y / 2);
		q3y = yDE(halfTime, vxa + deltaT * q2x / 2, vy + deltaT * q2y / 2);
		q4x = xDE(fullTime, vxa + deltaT * q3x, vy + deltaT * q3y);
		q4y = yDE(fullTime, vxa + deltaT * q3x, vy + deltaT * q3y);
		newVals[0] = vx + deltaT * (q1x + 2 * q2x + 2 * q3x + q4x) / 6;
		newVals[1] = vy + deltaT * (q1y + 2 * q2y + 2 * q3y + q4y) / 6;
		return newVals;
	} // end estimateVelocity

} // end RungeKuttaSolver