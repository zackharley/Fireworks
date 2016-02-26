/**
 * 
 * @author zackh_000
 * @version 1.0
 */
public class Star implements ODESystem {

	private double initialMass;		// kg
	private double burnRate; 		// kg/second
	private double starDensity;		// kg/m*m*m
	private double currentMass;		// kg
	private double radius;			// m
	private double velocityX;		// m
	private double velocityXA;		// m/s
	private double velocityY;		// m/s
	private double velocityMag;		// m/s
	private double dragForce;		// N

	public Star(double initialVX, double initialVXA, double initialVY, double airDensity, double dragCoeff) {
		setInitialMass();
		setBurnRate();
		setStarDensity();
		setCurrentMass();
		setRadius();
		setVelocityX(initialVX);
		setVelocityXA(initialVXA);
		setVelocityY(initialVY);
		setVelocityMag();
		setDragForce(airDensity, dragCoeff);
	} // end full constructor

	private void setInitialMass() {
		initialMass = 0.008;
	}

	private void setInitialMass(double mass) {
		initialMass = mass;
	}

	private void setBurnRate() {
		burnRate = 0.0030;
	}

	private void setStarDensity() {
		starDensity = 1900;
	}

	private void setCurrentMass() {
		currentMass = initialMass;
	}

	public void setCurrentMass(double time) {

		currentMass = initialMass - time * burnRate;
		System.out.println(currentMass + " = currentMass");
	} // end setMass Mutator

	public void setRadius() {
		double volume = currentMass / starDensity;
		radius = Math.pow(3 * volume / (4 * Math.PI), 1.0 / 3.0);
		System.out.println(volume + " = " + currentMass + " / " + starDensity);
	} // end setRadius Mutator

	public void setVelocityX(double velocity) {
		velocityX = velocity;
	}

	public void setVelocityXA(double velocity) {
		velocityXA = velocity;
	}

	public void setVelocityY(double velocity) {
		velocityY = velocity;
	}

	public void setVelocityMag() {
		velocityMag = Math.sqrt(velocityXA * velocityXA + velocityY * velocityY);
	} // end setVelocity Mutator

	public void setDragForce(double airDensity, double dragCoeff) {
		double area = Math.PI * radius * radius;
		System.out.println(airDensity + ", " + dragCoeff + ", " + area + ", " + velocityMag);
		dragForce = airDensity * velocityMag * velocityMag * area * dragCoeff / 2;
	} // end getDragForce

	public double getInitialMass() {
		return initialMass;
	}

	public double getBurnRate() {
		return burnRate;
	}

	public double getCurrentMass() {
		return currentMass;
	} // end getMass Accessor

	public double getRadius() {
		return radius;
	} //  end getRadius Accessor

	public double getVelocityX() {
		return velocityX;
	}

	public double getVelocityXA() {
		return velocityXA;
	}

	public double getVelocityY() {
		return velocityY;
	}

	public double getVelocityMag() {
		return velocityMag;
	} // end getVelocityMag Accessor

	public double getDragForce() {
		return dragForce;
	} // end getDragForce Accessor

	@Override
	public int getSystemSize() {
		return 2;
	}

	@Override
	public double[] getCurrentValues() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double[][] getFunction(double time, double[] values) {
		// TODO Auto-generated method stub
		return null;
	}

} // end Star class
