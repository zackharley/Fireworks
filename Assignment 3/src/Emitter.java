/**
 * A class used to create Firework Emitters.
 * @author Zack Harley
 * @version 1.0
 *
 */
import java.util.ArrayList;

public class Emitter extends Firework implements ODESystem{
	private final int SYSTEM_SIZE = 2;
	
	private double launchAngle;
	private double launchAngleVariation;
	private double exitVelocity;
	private int numToLaunch;
	private Particle launchType;
	
	/**
	 * Public constructor for the Emitter class.
	 * @param emitterPosition Starting position of the Emitter.
	 * @param emitterLaunchTime Launch time of the emitter, in seconds.
	 * @param launchTubeLifetime Lifetime of the emitter, in seconds.
	 * @param starVelocity Magnitude of the initial velocity of the Particle.
	 * @param emitterLaunchAngle Launch angle of the Emitter.
	 * @param starAngleVariation The variation in the launch angle depending of the star.
	 * @param numberToLaunch The number of Fireworks to launch.
	 * @param particleTemplate The particle template to follow when adding to the launch list.
	 * @throws EmitterException when the launch angle is not between -15 and 15 degrees, the star velocity is non-positive, 
	 * or the number of stars to launch is non-positive. 
	 */
	public Emitter(double[] emitterPosition, int emitterLaunchTime, double launchTubeLifetime, double starVelocity, double emitterLaunchAngle,
			double starAngleVariation, int numberToLaunch, Particle particleTemplate) throws EmitterException {
		super(emitterPosition, emitterLaunchTime, launchTubeLifetime);
		if(emitterLaunchAngle < -15 || emitterLaunchAngle > 15)
			throw new EmitterException("Launch angle " + emitterLaunchAngle + " isn't between -15 and 15 degrees");
		launchAngle = emitterLaunchAngle;
		if(starVelocity <= 0)
			throw new EmitterException("Star velocity " + starVelocity + " is not greater than 0 m/s.");
		exitVelocity = starVelocity;
		launchAngleVariation = starAngleVariation;
		if(numberToLaunch <= 0) {
			throw new EmitterException("Number of particles to launch must be a positive integer.");
		}
		numToLaunch = numberToLaunch;
		launchType = particleTemplate;
	} // end Emitter constructor
	
	/**
	 * A method used to launch Particles from the Emitter.
	 * @param time Then starting time of the launch
	 * @return A list of Particles that were launched.
	 */
	public ArrayList<Particle> launch(double time) {
		ArrayList<Particle> particles = new ArrayList<>();
		for(int i = 0; i < numToLaunch; i++){
			Particle particle = launchType.clone();
			particle.setPosition(launchType.getPosition());
			particle.setVelocity(launchType.getVelocity());
			particles.add(particle);
		}
		return particles;
	} // end launch
	
	/**
	 * Allows random launch angle generation.
	 * @return Random launch angle.
	 */
	public double getRandomLaunchAngle() {
		return launchAngle + launchAngleVariation * 2 * (Math.random() - 0.5);
	} // end getRandomLaunchAngle

	/**
	 * Allows random launch velocity generation.
	 * @return Random launch velocity.
	 */
	public double getRandomExitVelocity() {
		return exitVelocity - 0.1 * exitVelocity * (Math.random() - 0.5);
	} // end getRandomExitVelocity

	@Override
	public int getSystemSize() {
		return SYSTEM_SIZE;
	} // end getSystemSize
	@Override
	public double[] getCurrentValues() {
		double[] values = launchType.getVelocity();
		return values;
	} // end getCurrentValues
	@Override
	public double[] getFunction(double time, double[] values, Environment env) {
		double[] functionVal = new double[SYSTEM_SIZE];
		double vX = values[0];
		double vY = values[1];
		functionVal[0] = launchType.xDE(time, vX, vY, env, launchType.getMass());
		functionVal[1] = launchType.yDE(time, vX, vY, env, launchType.getMass());
		return functionVal;
	} // end getFunction
	
} // end Emitter class
