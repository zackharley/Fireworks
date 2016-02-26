/**
 * 
 * @author zackh_000
 * @version 1.0
 */
public class LaunchTube {

	private double launchAngle;		// degrees
	private double launchVX;		// m/s
	private double launchVY;		// m/s
	private double launchVMag;		// m/s

	public LaunchTube(double angle) throws IllegalLaunchAngleException {
		if (angle < -15 || angle > 15) {
			throw new IllegalLaunchAngleException("The supplied launch angle was not" +
					"between -15 and 15 degrees, inclusive.");
		}
		setLaunchAngle(angle);
		setLaunchVX();
		setLaunchVY();
		setLaunchVMag();
	} // end full constructor

	private void setLaunchAngle(double angle) {
		launchAngle = angle;
	}
	
	// TODO set launch velocities
	
	public double getLaunchAngle() {
		return launchAngle;
	}
	
	public double getLaunchVX() {
		return launchVX;
	}
	
	public double getLaunchVY() {
		return launchVY;
	}
	
	public double getLaunchVMag() {
		return launchVMag;
	}

} // end LaunchTube class
