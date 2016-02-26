/**
 * 
 * @author zackh_000
 * @version 1.0
 */
public class Environment {
	
	private double windVelocity;	// m/s
	private double airDensity;		// kg/m*m*m
	private double dragCoeff;		// unitless
	
	public Environment(double windV) throws IllegalWindVelocityException {
		if (windV > 20 || windV < 20){
			throw new IllegalWindVelocityException("The supplied wind velocity was not" +
					"between -20 and 20 m/s, inclusive.");
		}
		setWindVelocity(windV);
		setAirDensity();
		setDragCoeff();
	} // end full constructor

	private void setWindVelocity(double velocity) {
		windVelocity = velocity;
	}
	
	private void setAirDensity() {
		airDensity = 1.2;
	}
	
	private void setDragCoeff() {
		dragCoeff = 0.4;
	}
	
	public double getWindVelocity() {
		return windVelocity;
	}
	
	public double getAirDensity() {
		return airDensity;
	}
	
	public double getDragCoeff() {
		return dragCoeff;
	}
	
} // end Environment class
