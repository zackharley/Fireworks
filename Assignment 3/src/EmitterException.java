/**
 * An exception that is used by the Emitter class
 * @author Zack Harley
 * @version 1.0
 */
public class EmitterException extends Exception {

	private static final long serialVersionUID = 5778086627790210282L;

	/**
	 * Obtains a useful string message when thrown.
	 * @param message
	 */
	public EmitterException (String message) {
		super(message);
	} // end constructor
	
}
