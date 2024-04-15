/**
 * This class is used to test the stats library class and all the methods used inside.
 * 
 * @author Melvin Vazquez
 */
package statsLibrary;
import static org.junit.Assert.*;

import org.junit.Test;

public class TestStatsLibrary {
	/**
	 * This method creates the test variable and calls the testCases method that was made inside stats library
	 * @param args
	 */
	public static void main(String[]args) {
		StatsLibrary test = new StatsLibrary();
		test.testCases();
	}
}