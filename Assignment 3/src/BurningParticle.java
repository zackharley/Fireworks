/**
 * This class describes a burning particle with shrinking radius and burning mass.
 * @author Zack Harley
 * @version 1.0
 */
import javafx.scene.paint.Color;

public class BurningParticle extends Particle{
	private final double DRAG_COEFF = 0.4;		// unitless
	private double burnRate;					// kg/second
	private double density;						// kg/m*m*m
	private double startingMass;				// kg

	/**
	 * Public constructor for the BurningParticle class
	 * @param starMass The initial mass, in kg, of the burning particle.
	 * @param color The color of the burning particle.
	 * @param starBurnRate The burn rate, in kg/s, of the particle.
	 * @param starDensity The desnity, in kg/m^3, of the particle
	 */
	public BurningParticle(double starMass, Color color, double starBurnRate, double starDensity) {
		super(starMass / starBurnRate, starMass, Math.pow(3 * (starMass / starDensity) / (4 * Math.PI), 1.0 / 3.0), color);
		burnRate = starBurnRate;
		density = starDensity;
		startingMass = starMass;
	} // end BurningParticle constructor

	// Returns the mass of the star at the supplied time.
	// Time is in seconds and the returned mass in kg.
	private double getMass(double time) {
		return startingMass - time * burnRate;
	} // end getMass

	// Returns the radius of the star at the supplied time.
	// Time is in seconds and the returned radius is in metres.
	private double getRadius(double time) {
		double volume = getMass(time) / density;
		return Math.pow(3 * volume / (4 * Math.PI), 1.0 / 3.0);
	} // end getRadius

	// Calculates the magnitude of the drag force on the star, given time in
	// seconds and the two velocity components in m/sec.
	private double getDragForce(double time, double vx, double vy) {
		double velocityMag = getVelocityMag(vx, vy);
		double radius = getRadius(time);
		double area = Math.PI * radius * radius;
		return Environment.DENSITY_AIR * velocityMag * velocityMag * area * DRAG_COEFF / 2;
	} // end getDragForce

	/**
	 * This method returns the value of the fx function.
	 * @param time Current time, in seconds.
	 * @param vx Current x velocity.
	 * @param vy Current y velocity.
	 * @param env The BurningParticle's current Evironment.
	 * @param mass The current mass of the star.
	 * @param dragForce The magnitude of the drag force acting on the star.
	 * @return The value of the fx function
	 */
	public double xDE(double time, double vx, double vy, Environment env, double mass, double dragForce) {
		// Use apparent x velocity to calculate drag.
		double vxa = vx - env.getWindVelocity();
		double velocityMag = getVelocityMag(vxa, vy);
		return -dragForce * vxa / (mass * velocityMag);
	} // end xDE

	/**
	 * This method returns the value of the fy function.
	 * @param time Current time, in seconds.
	 * @param vx Current x velocity.
	 * @param vy Current y velocity.
	 * @param env The BurningParticle's current Evironment.
	 * @param mass The current mass of the star.
	 * @param dragForce The magnitude of the drag force acting on the star.
	 * @return The value of the fy function
	 */
	public double yDE(double time, double vx, double vy, Environment env, double mass, double dragForce) {
		// Use apparent x velocity to calculate drag.
		double vxa = vx - env.getWindVelocity();
		double velocityMag = getVelocityMag(vxa, vy);
		return -Environment.G - dragForce * vy / (mass * velocityMag);
	} // end yDE

} // end BurningParticle class
