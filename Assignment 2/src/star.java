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
	private double velocityXA;		// m/s
	private double velocityY;		// m/s
	private double velocityMag;		// m/s
	private double dragForce;		// N

	public Star(double initialVX, double initialVY, double airDensity, double dragCoeff) {
		setInitialMass();
		setBurnRate();
		setStarDensity();
		setCurrentMass();
		setRadius();
		setVelocityXA(initialVX);
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
		initialMass = currentMass;
	}

	public void setCurrentMass(double time) {
		setInitialMass(currentMass);
		currentMass = initialMass - time * burnRate;
	} // end setMass Mutator

	public void setRadius() {
		double volume = getCurrentMass() / starDensity;
		radius = Math.pow(3 * volume / (4 * Math.PI), 1.0 / 3.0);
	} // end setRadius Mutator

	private void setVelocityXA(double velocity) {
		velocityXA = velocity;
	}

	private void setVelocityY(double velocity) {
		velocityY = velocity;
	}

	public void setVelocityMag() {
		velocityMag = Math.sqrt(velocityXA * velocityXA + velocityY * velocityY);
	} // end setVelocity Mutator

	public void setDragForce(double airDensity, double dragCoeff) {
		double velocityMag = getVelocityMag();
		double radius = getRadius();
		double area = Math.PI * radius * radius;
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
	
	public static double[][] getPath (double vxInitial, double vyInitial, double vWind, Star st) {
		int numPoints = (int)Math.round(st.getInitialMass() / st.getBurnRate() / ParticleManager.getDeltaT() ) + 1;
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

	@Override
	public double[] getCurrentValues() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double[] getFunction(double time, double[] values) {
		// TODO Auto-generated method stub
		return null;
	}

} // end Star class
