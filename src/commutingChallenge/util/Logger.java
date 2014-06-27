/**
 * 
 */
package commutingChallenge.util;

/**
 * @author Abhishek Waichal
 * Class Debug used for logging purposes.
 */
public class Logger {
	private static int DEBUG_VALUE;

	/**
	 * Setter method(mutator) for DEBUG_VALUE
	 * 
	 * @param value
	 * - set the DEBUG_VALUE to this
	 */

	public static void setDebugValue(int value) {

		DEBUG_VALUE = value;

	}

	/**
	 * Getter(accessor) for DEBUG_VALUE
	 * 
	 * @return - returns the DEBUG_VALUE
	 */

	public static int getDebugValue() {

		return DEBUG_VALUE;

	}

	/**
	 * Prints the contents as per the debug value
	 * 
	 * @param value
	 * - DEBUG_VALUE
	 * @param Message
	 * - message to print
	 */

	public static void printDebug(int value, String Message) {

		if (getDebugValue() == value) {
			switch (value) {
			case 0:
				System.out.println(Message);
				break;
			case 1:
				System.err.println(Message);
				break;
			default:
				break;
			}
		}
	}
}