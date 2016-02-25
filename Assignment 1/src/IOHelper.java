import java.util.InputMismatchException;
import java.util.Scanner;

public class IOHelper {
	
	private static Scanner screenInput = new Scanner(System.in);

	public static int getInt(int low, String prompt, int high){
		int userNum = 0;
		boolean inputOK = false;
		String dump = null;
		
		do{
			System.out.print(prompt);
			inputOK = false;
			try{
				userNum = screenInput.nextInt();
				inputOK = true;
			} catch(InputMismatchException e) {
				dump = screenInput.nextLine();
				System.out.println("\"" + dump + "\" is not a valid integer. Please try again!");
				userNum = low;
			} //  end try-catch
			if(userNum > high || userNum < low){
				System.out.println("The number is not within the legal limits. Please try again!");
			}
		} while(!inputOK || userNum < low || userNum > high); // indicate why user is being prompted again
		return userNum;
	} //  end getInt 3-input method
	
	public static int getInt(int low, String prompt){
		int high= Integer.MAX_VALUE;
		return getInt(low, prompt, high);
	} // end getInt 2-input (low, prompt) method
	
	public static int getInt(String prompt, int high){
		int low = Integer.MIN_VALUE;
		return getInt(low, prompt, high);
	} // end getInt 2-input (prompt, high) method
	
	public static int getInt(String prompt){
		int low = Integer.MIN_VALUE;
		int high = Integer.MAX_VALUE;
		return getInt(low, prompt, high);
	} // end getInt 1-input method
	
	public static int getInt(){
		int low = Integer.MIN_VALUE;
		int high = Integer.MAX_VALUE;
		String prompt = "Enter an integer between" + low + " and " + high + ": ";
		return getInt(low, prompt, high);
	} // end getInt 0-input method
	
	public static double getDouble(double low, String prompt, double high){
		double userNum = 0;
		boolean inputOK = false;
		String dump = null;
		
		do{
			System.out.print(prompt);
			inputOK = false;
			try{
				userNum = screenInput.nextDouble();
				inputOK = true;
			} catch(InputMismatchException e) {
				dump = screenInput.nextLine();
				System.out.println("\"" + dump + "\" is not a valid double. Please try again!");
				userNum = low;
			} //  end try-catch
			if(userNum > high || userNum < low){
				System.out.println("The number is not within the legal limits. Please try again!");
			}
		} while(!inputOK || userNum < low || userNum > high); // indicate why user is being prompted again
		return userNum;
	}

	public static double getDouble(double low, String prompt) {
		double high = Double.MAX_VALUE;
		return getDouble(low, prompt, high);
	}
	
	public static double getDouble(String prompt, double high) {
		double low = Double.MIN_VALUE;
		return getDouble(low, prompt, high);
	}
	
	public static double getDouble(String prompt) {
		double low = Double.MIN_VALUE;
		double high = Double.MAX_VALUE;
		return getDouble(low, prompt, high);
	}
	
	public static double getDouble() {
		double low = Double.MIN_VALUE;
		double high = Double.MAX_VALUE;
		String prompt = "Please enter number between " + low +  " and " + high + ": ";
		return getDouble(low, prompt, high);
	}
}
