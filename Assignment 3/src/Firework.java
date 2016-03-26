/**
 * A class used to create new fireworks objects
 * @author Zack Harley
 *
 */
public class Firework {
	private double[] position = new double[2];
	private double creationTime;
	private double lifetime;
	 
	/**
	 * Constructor for the Firework class.
	 * @param pos The starting position of the Firework.
	 * @param creation The time, in seconds, that the Firework is being launched at.
	 * @param life The lifetime, in seconds, of the Firework.
	 */
	public Firework(double[] pos, double creation, double life) {
		setPosition(pos);
		creationTime = creation;
		setLifetime(life);
	} // end Firework constructor

	/**
	 * The accessor for the position variable.
	 * @return The current position of the Firework.
	 */
	public double[] getPosition() {
		return position;
	} // end getPosition

	/**
	 * A mutator for the position variable.
	 * @param position An array of size 2 containing the new x and y coordinates of the Firework.
	 */
	public void setPosition(double[] position) {
		this.position = position;
	} // end setPosition

	/**
	 * The accessor for the lifetime variable.
	 * @return The lifetime of the Firework, in seconds.
	 */
	public double getLifetime() {
		return lifetime;
	} // end getLifetime

	/**
	 * A mutator for the lifetime variable.
	 * @param lifetime The new lifetime of the Firework, in seconds.
	 */
	public void setLifetime(double lifetime) {
		this.lifetime = lifetime;
	} // end setLifetime
	
} // end Firework class
