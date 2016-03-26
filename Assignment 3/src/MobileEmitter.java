/**
 * A class to create MobileEmitters that will launch Streaks after the initial launch.
 * @author zackh_000
 *
 */
public class MobileEmitter extends Emitter{
	private Particle particleToFollow;

	/**
	 * A constructor for the MobileEmitter class.
	 * @param starVelocity The starting velocity of the MobileEmitter, in m/s.
	 * @param emmiterLaunchTime The time, in seconds, of the launch.
	 * @param starAngleVariation The variation of the launch angle due to the star.
	 * @param numberToLaunch The number of stars to launch
	 * @param particleTemplate The star template to follow when filling the Emitter
	 * @param theStar The star for the MobileEmitter to follow.
	 * @throws EmitterException
	 */
	public MobileEmitter(double starVelocity, int emmiterLaunchTime, double starAngleVariation, int numberToLaunch,
			Particle particleTemplate, BurningParticle theStar) throws EmitterException {
		super(theStar.getPosition(), emmiterLaunchTime, theStar.getLifetime(), starVelocity, 0.0, starAngleVariation, numberToLaunch, particleTemplate);
		particleToFollow = theStar;
	} // end MobileEmitter constructor.
	
} // end MobileEmitter class
