/**
 * This is the class for any particles.
 * @author zackh_000
 * @version 1.0
 */
import javafx.scene.paint.Color;

public class Particle extends Firework implements ODESystem{
	private final static double[] DEFAULT_POS = {0, 0};
	private final static double DEFAULT_CREATION = 0;
	private final int SYSTEM_SIZE = 2;
	private final double dragForce = 2;

	private double[] velocity = new double[2];		// m/s
	private double  radius;							// m
	private double mass;							// kg
	private Color colour;

	/**
	 * Public constructor for the Particle class.
	 * @param lifetime The lifetime of the particle, in seconds.
	 * @param particleMass The mass of the particle, in kilograms.
	 * @param particleRadius The radius of the particle in meters.
	 * @param particleColor The color of the current particle
	 */
	public Particle(double lifetime, double particleMass, double particleRadius, Color particleColor) {
		super(DEFAULT_POS, DEFAULT_CREATION, lifetime);		
		radius = particleRadius;
		setMass(particleMass);
		colour = particleColor;
	} // end Particle constructor


	@Override
	public int getSystemSize() {
		return SYSTEM_SIZE;
	} // end getSystemSize
	@Override
	public double[] getCurrentValues() {
		double[] values = new double[SYSTEM_SIZE];
		values[0] = getVelocity()[0];
		values[1] = getVelocity()[1];
		return values;
	} // end getCurrentValues
	@Override
	public double[] getFunction(double time, double[] values, Environment env) {
		double[] functionVal = new double[SYSTEM_SIZE];
		double vX = values[0];
		double vY = values[1];
		functionVal[0] = xDE(time, vX, vY, env, getMass());
		functionVal[1] = yDE(time, vX, vY, env, getMass());
		return functionVal;
	} // end getFunction

	/**
	 * Gets the magnitude of the given velocity components.
	 * @param vx X velocity component.
	 * @param vy Y velocity component.
	 * @return Magnitude of the velocity.
	 */
	public double getVelocityMag(double vx, double vy) {
		return Math.sqrt(vx * vx + vy * vy);
	} // end getVelocity

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
	public double xDE(double time, double vx, double vy, Environment env, double mass) {
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
	public double yDE(double time, double vx, double vy, Environment env, double mass) {
		// Use apparent x velocity to calculate drag.
		double vxa = vx - env.getWindVelocity();
		double velocityMag = getVelocityMag(vxa, vy);
		return -Environment.G - dragForce * vy / (mass * velocityMag);
	} // end yDE

	/**
	 * An accessor for the colorr variable.
	 * @return The colour of the particle
	 */
	public Color getColour() {
		return colour;
	} // end getColour

	/**
	 * A mutator for the colour variable.
	 * @param color The new colour of the particle.
	 */
	public void setColour(Color color) {
		this.colour = color;
	} //end setColour

	/**
	 * Checks to see if the particle is still alive, or burning.
	 * @param time The current time, in seconds.
	 * @return True or false depending on if the star is still burning.
	 */
	public boolean isAlive(double time) {
		if(getMass() > 0 && radius > 0)
			return true;
		return false;
	} // end isAlive
	
	/**
	 * A mutator that updates the current position of the star.
	 * @param newValues An array containing the current velocity components for the star in m/sec.
	 * @param deltaTime The time interval in seconds.
	 * @param env An instance of the current Environment object is needed to supply the
	 * wind velocity, which is used to calculate the apparent velocity. This allows the wind
	 * velocity to change during a simulation.
	 */
	public void updatePosition(double time, double deltaTime, Environment env) {
		velocity = RungeKuttaSolver.getNextPoint(this, time, deltaTime, env);
		double[] position = {getPosition()[0] + velocity[0] * deltaTime, getPosition()[1] + velocity[1] * deltaTime};
		setPosition(position);
	} // end updatePosition
	
	@Override
	public Particle clone() {
		Particle newParticle = null;
		newParticle = new Particle(getLifetime(), getMass(), radius, colour);
		return newParticle;
	}

	/**
	 * An accessor for the mass variable.
	 * @return The current mass of the particle, in kg.
	 */
	public double getMass() {
		return mass;
	} // end getMass

	/**
	 * A mutator for the mass variable.
	 * @param mass The new mass of the particle, in kg.
	 */
	public void setMass(double mass) {
		this.mass = mass;
	} // end setMass

	/**
	 * Accessor for the velocity variable.
	 * @return The current velocity, in m/s.
	 */
	public double[] getVelocity() {
		return velocity;
	} // end getVelocity


	/**
	 * A mutator for the velocity variable.
	 * @param velocity The new velocity of the particle, in m/s.
	 */
	public void setVelocity(double[] velocity) {
		this.velocity = velocity;
	} // end setVelocity

} // end Particle class
