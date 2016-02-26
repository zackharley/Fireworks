/**
 * 
 * @author zackh_000
 * @version 1.0
 */
public class LaunchTube {

	private double launchAngle;		// degrees
	private double launchVX;		// m/s
	private double launchVXA;		// m/s
	private double launchVY;		// m/s
	private double launchVMag;		// m/s

	public LaunchTube(double angle, double windV) throws IllegalLaunchAngleException {
		if (angle < -15 || angle > 15) {
			throw new IllegalLaunchAngleException("The supplied launch angle was not " +
					"between -15 and 15 degrees, inclusive.");
		}
		setLaunchAngle(angle);
		setLaunchVMag();
		setLaunchVX(angle);
		setLaunchVXA(angle, windV);
		setLaunchVY(angle);
	} // end full constructor

	private void setLaunchAngle(double angle) {
		launchAngle = angle;
	}
	
	private void setLaunchVMag() {
		launchVMag = 22;
	}
	
	private void setLaunchVX(double angle) {
		launchVX = getLaunchVMag() * Math.sin(angle);
	}
	
	private void setLaunchVXA(double angle, double windV) {
		launchVXA = (getLaunchVMag() * Math.sin(angle)) - windV;
	}
	
	private void setLaunchVY(double angle) {
		launchVY = getLaunchVMag() * Math.cos(angle);
	}
	
	public double getLaunchAngle() {
		return launchAngle;
	}
	
	public double getLaunchVX() {
		return launchVX;
	}
	
	public double getLaunchVXA() {
		return launchVXA;
	}
	
	public double getLaunchVY() {
		return launchVY;
	}
	
	public double getLaunchVMag() {
		return launchVMag;
	}

} // end LaunchTube class
