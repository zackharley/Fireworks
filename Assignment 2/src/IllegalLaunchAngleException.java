// TODO Add object name that throws Exception
/**
 * An Exception thrown by the  object if parameters are not legal.
 * <ul>
 * <li>The launch angle must between -15 and 15</li>
 * </ul>
 * @author Zackery Harley
 * @version 1.0
 */
public class IllegalLaunchAngleException extends Exception {

	private static final long serialVersionUID = -3645990600117899343L;

	/**
	 * Supplies a default message.
	 */
	public IllegalLaunchAngleException() {
		super("Illegal parameter calue supplied to  object.");
	}
	
	/**
	 * Passes along the message supplied to the exception.
	 * @param message A more specific message.
	 */
	public IllegalLaunchAngleException(String message) {
		super(message);
	}
	
} // end IllegalLaunchAngleException
