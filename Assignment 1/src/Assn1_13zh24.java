/*
 * A program to calculate the x-axis and y-axis positions of Roman Candle stars at specific times.
 * The program prompts the user for a launch angle and wind velocity. This is then used to calculate the
 * positions of the star at various times at intervals of 0.5 seconds. There are two ways that these
 * positions are calculated: using Newtonian physics, which takes into account only gravity, and using
 * Non-Newtonian physics, where drag and wind velocity are used. After the calculations, the times, x 
 * positions, and y positions are all outputted to a text file.
 * 
 * By Zackery Harley (10135795) - for CMPE 212
 */

import java.util.Arrays;

public class Assn1_13zh24 {
	public final static int LAUNCH_VELOCITY = 22; 							// [m/s]
	public final static double GRAVITY_ACCELERATION = 9.807; 				// [m/s^2]
	public final static double FLUID_DENSITY = 1.2; 						// [kg/m^3]
	public final static double DRAG_COEFFICIENT = 0.4; 						// No units 
	public final static double STAR_RADIUS = 0.08;							// [m] - Assumed from assignment background
	public final static double STAR_MASS = 0.008;							// [kg]
	public final static double STAR_BURNRATE = 0.003; 						// [kg/s]
	public final static int STAR_DENSITY = 1900;							// [kg/m^3]
	public final static int WIND_VELOCITY_MAX = 20; 						// [km/h]
	public final static int WIND_VELOCITY_MIN = -20; 						// [km/h] 
	public final static int LAUNCH_ANGLE_MAX = 15; 							// [degrees] - from the vertical
	public final static int LAUNCH_ANGLE_MIN = -15; 						// [degrees] - from the vertical
	public final static double STEP = 0.5;									// [s] - step size for time range
	public final static double[] TIME_RANGE = {0, 0.5, 1, 1.5, 2, 2.5, 3}; 	// [s] - used to measure position and velocity
	public final static String PROMPT_WIND_VELOCITY = "Please enter a wind velocity between " + WIND_VELOCITY_MIN + 
			" and " + WIND_VELOCITY_MAX + " meters per second: ";
	public final static String PROMPT_LAUNCH_ANGLE = "Please enter a launch angle between " + LAUNCH_ANGLE_MIN + 
			" and " +  LAUNCH_ANGLE_MAX + " degrees from the vertical: ";
	public final static String NEWTONIAN_HEADER = "#-----------------Newtonian------------------";
	public final static String NON_NEWTONIAN_HEADER = "#---------------Non-Newtonian----------------";
	public final static String OUTPUTS_HEADER = "Time[s]\t\tX Position[m]\tY Position[m]";
	
	public static void main(String[] args) {
		showInstructions();
		double launchAngle = getLaunchAngle();
		double windVelocity = getWindVelocity();
		newtonian(launchAngle);
		nonNewtonian(launchAngle, windVelocity);

	} // end main 
	
	// This method displays the program instructions on the console for the user
	public static void showInstructions(){
		System.out.println("This program calculates the x and y positions at times " + Arrays.toString(TIME_RANGE) + ".");
		System.out.println("You will be asked for inputs of two launch angles and a wind velocity. This will allow the");
		System.out.println("positions to be calculated using Newtonian and Non-Newtonian physics. We will then output");
		System.out.println("the results to a tab-delimted text file, which you will name. Let's start!\n");
	}
	
	// This method gets the launch angle as an input from the console using the IOHelper class
	public static double getLaunchAngle() {
		return IOHelper.getDouble(LAUNCH_ANGLE_MIN, PROMPT_LAUNCH_ANGLE, LAUNCH_ANGLE_MAX);
	}
	
	// This method gets the wind velocity as an input from the console using the IOHelper class
	public static double getWindVelocity(){
		return IOHelper.getDouble(WIND_VELOCITY_MIN, PROMPT_WIND_VELOCITY, WIND_VELOCITY_MAX);
	}
	
	// This method calculates the x and y positions of the star at times between 0 and 3 seconds using Newtonian physics
	// It also uses the getLaunchAngle method to get a launch angle 
	public static void newtonian(double launchAngle){
		double[] xPositions = new double[TIME_RANGE.length];
		double[] yPositions = new double[TIME_RANGE.length];
		double xComponentVelocity = getXInitialVelocity(launchAngle);
		double yComponentVelocity = getYInitialVelocity(launchAngle);
		int i;
		for(i=0; i < TIME_RANGE.length; i++){
			xPositions[i] = getXPosNewton(xComponentVelocity, TIME_RANGE[i]);
			yPositions[i] = getYPosNewton(yComponentVelocity, TIME_RANGE[i]);	
		}
		
		System.out.println("x = " + Arrays.toString(xPositions));
		System.out.println("y = " + Arrays.toString(yPositions));
	}
	
	
	
	public static void nonNewtonian(double launchAngle, double windVelocity) {
		double[] xPositions = new double[TIME_RANGE.length];
		double[] yPositions = new double[TIME_RANGE.length];
		double[] xVelocities = new double[TIME_RANGE.length];
		double[] yVelocities = new double [TIME_RANGE.length];
		double[][] q = new double[2][4];
		xPositions[0] = 0;
		yPositions[0] = 0;
		xVelocities[0] = getXInitialVelocity(launchAngle);
		yVelocities[0] = getYInitialVelocity(launchAngle);
		for(int i = 1; i < TIME_RANGE.length; i++) {
			getQ(TIME_RANGE[i - 1], xVelocities[i - 1], yVelocities[i - 1], q);
			xVelocities[i] = getXVelocity(xVelocities[i - 1], q, windVelocity);
			yVelocities[i] = getYVelocity(yVelocities[i - 1], q);
			xPositions[i] = getPositionNonNewton(xPositions[i - 1], xVelocities[i]);
			yPositions[i] = getPositionNonNewton(yPositions[i - 1], yVelocities[i]);
		}
		System.out.println("x = " + Arrays.toString(xPositions));
		System.out.println("y = " + Arrays.toString(yPositions));
	}
	
	public static double getXVelocity(double currentVelocity, double[][] q, double windVelocity) {
		return ((currentVelocity + (1 / 12 * (q[0][0] + q[0][1] + q[0][2] + q[0][3]))) - windVelocity);
	}
	
	public static double getYVelocity(double currentVelocity, double[][] q) {
		return (currentVelocity + (1 / 12 * (q[0][0] + q[0][1] + q[0][2] + q[0][3])));
	}
	
	
	public static void getQ(double time, double xVelocity, double yVelocity, double[][] q) {
		q[0][0] = xNonNewton(time, xVelocity, yVelocity);
		q[1][0] = yNonNewton(time, xVelocity, yVelocity);
		q[0][1] = xNonNewton(time + 0.25, xVelocity + (q[0][0] / 4), yVelocity + (q[1][0] / 4));
		q[1][1] = yNonNewton(time + 0.25, xVelocity + (q[0][0] / 4), yVelocity + (q[1][0] / 4));
		q[0][2] = xNonNewton(time + 0.25, xVelocity + (q[0][1] / 4), yVelocity + (q[1][1] / 4));
		q[1][2] = yNonNewton(time + 0.25, xVelocity + (q[0][1] / 4), yVelocity + (q[1][1] / 4));
		q[0][3] = xNonNewton(time + 0.5, xVelocity + (q[0][2] / 2), yVelocity + (q[1][2] / 2));
		q[1][3] = yNonNewton(time + 0.5, xVelocity + (q[0][2] / 2), yVelocity + (q[1][2] / 2));
	}
	
	public static double xNonNewton(double time, double xVelocity, double yVelocity) {
		double vSquared = Math.pow(getVelocityMagnitude(xVelocity, yVelocity), 2);
		double mass = getMass(time);
		double radius = getRadius(mass);
		double csArea = getCSArea(radius);
		return -(FLUID_DENSITY * vSquared * csArea * DRAG_COEFFICIENT / 2);
	}
	
	public static double yNonNewton(double time, double xVelocity, double yVelocity) {
		double vSquared = Math.pow(getVelocityMagnitude(xVelocity, yVelocity), 2);
		double mass = getMass(time);
		double radius = getRadius(mass);
		double csArea = getCSArea(radius);
		return -GRAVITY_ACCELERATION - (FLUID_DENSITY * vSquared * csArea * DRAG_COEFFICIENT / 2);
	}
	
	public static double getVelocityMagnitude(double xVelocity, double yVelocity){
		return Math.sqrt(Math.pow(xVelocity, 2) + Math.pow(yVelocity, 2));
	}
	
	public static double getDragForce(double velocity, double time) {
		double radius = 8;
		return FLUID_DENSITY * Math.pow(velocity, 2) * Math.PI * radius * DRAG_COEFFICIENT / 2;
	}
	
	public static double getMass(double time) {
		return STAR_MASS - (STAR_BURNRATE * time);
	}
	
	public static double getRadius(double mass) {
		return Math.cbrt(3 * STAR_DENSITY / (4 * Math.PI * mass));
	}
	
	public static double getCSArea(double radius) {
		return Math.PI * Math.pow(radius, 2);
	}

	public static double getPositionNonNewton(double currentPos, double velocity) {
		return currentPos + (velocity / 2);
	}
	
	// This method calculates the x component of a velocity vector given the launch angle
	public static double getXInitialVelocity(double launchAngle){
		return LAUNCH_VELOCITY * Math.sin(Math.toRadians(launchAngle));
	}
	
	// This method calculates the x component of a velocity vector given the launch angle
	public static double getYInitialVelocity(double launchAngle){
		return LAUNCH_VELOCITY * Math.cos(Math.toRadians(launchAngle));
	}
	
	// This method returns the x position of a star given the x component of velocity and the time.
	// This is according to Newtonian physics
	public static double getXPosNewton(double xComponentVelocity, double time){
		return xComponentVelocity * time;
	}
	
	public static double getYPosNewton(double yComponentVelocity, double time){
		return (yComponentVelocity * time) - (0.5 *  GRAVITY_ACCELERATION * Math.pow(time, 2));
	}
	
	public static String fileSave(double[] x, double[] y) {

		return "Done";
	} // end method that saves results to text file

} // end class Ass1_13zh24
