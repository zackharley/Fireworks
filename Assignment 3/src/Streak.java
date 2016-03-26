import javafx.scene.paint.Color;
/**
 * A class used to generate streaking particles that launch from the initial Firework after launch
 * @author Zack Harley
 * @version 1.0
 *
 */
public class Streak extends Particle{
	private double[] origin = new double[2];
	
	 /**
	  * A constructor for the Streak class.
	  * @param position The starting position of the Streak.
	  * @param streakLifetime The lifetime of the Streak, in seconds.
	  * @param sparkMass The mass of the Streak, in kg.
	  * @param streakRadius The radius of the Streak, in m.
	  * @param sparkColour The colour of the Streak
	  */
	public Streak(double[] position, double streakLifetime, double sparkMass, double streakRadius, Color sparkColour) {
		super(streakLifetime, sparkMass, streakRadius, sparkColour);
	} // end Streak constructor
	
} // end Streak class
