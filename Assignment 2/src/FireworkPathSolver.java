/**
 * 
 * @author zackh_000
 * @version 1.0
 */

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FireworkPathSolver {

	private static Environment env;
	private static LaunchTube tube;
	private static Star st;
	private static ParticleManager manager;

	// Rounds a double number to the supplied number of digits after the decimal place.
	public static double round(double num, int digits) {
		double factor = Math.pow(10.0, digits);
		return Math.round(num * factor) / factor;
	}

	// Save data to a tab delimited text file. The dataType String is used to generate
	// the filename.
	public static void writeData(String dataType, double[][] points) {
		String outputFile = dataType + ".txt";
		Path file = Paths.get(outputFile);
		try (BufferedWriter writer = Files.newBufferedWriter(file)) {
			writer.write("seconds\tx (metres)\ty (metres)\r\n");
			for (double[] row : points)
				writer.write(round(row[0], 3) + "\t" + round(row[1], 3) +
						"\t" + round(row[2], 3) + "\r\n"); 
		} catch (IOException err) {
			System.err.println(err.getMessage());
		}
	} // end writeData

	public static void main(String[] args) {
		String prompt = "Enter the cross-wind velocity in km/hour, between -20 and 20: ";
		double wind = IOHelper.getDouble(-20, prompt, 20);
		prompt = "Enter the firing angle in degrees between -15 and 15: ";
		double angle = IOHelper.getDouble(-15, prompt, 15);
		wind = wind * 1000. / 3600.;		// convert to m/sec
		angle = angle * Math.PI / 180.;		// convert to radians
		
		try {
			env = new Environment(wind);
		} catch (IllegalWindVelocityException e) {
			System.err.println(e.getMessage());
		}
		try {
			tube = new LaunchTube(angle);
		} catch (IllegalLaunchAngleException e) {
			System.err.println(e.getMessage());
		}
		st = new Star(tube.getLaunchVX(), tube.getLaunchVY(), env.getAirDensity(), env.getDragCoeff());
		try {
			manager = new ParticleManager(st);
		} catch (IllegalTimeIntervalException e) {
			System.err.println(e.getMessage());
		}
	} // end main method

} // end FireworkPathSolver class
