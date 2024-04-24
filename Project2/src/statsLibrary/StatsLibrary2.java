package statsLibrary;

public class StatsLibrary2 {
	StatsLibrary getClass = new StatsLibrary();
	public double poissonDist(double lambda, double y) {
		double numerator = (Math.pow(lambda, y))*Math.pow(Math.E,-lambda);
		double denominator = getClass.factorial(y);
		double result = numerator/denominator;
		return result;
	}
	/**
	 * This just returns what is given because that is the expected. The way to find average is by getting the total number of events and dividing by the number of units.
	 * @param lambda - the rate/average times an event occurs
	 * @return lambda - returns the rate/average
	 */
	public double expectedPoisson(double lambda) {
		return lambda;
	}
	/**
	 * This just returns what is given because that is the variance. The way to find average is by getting the total number of events and dividing by the number of units.
	 * @param lambda - the rate/average times an event occurs
	 * @return lambda - returns the rate/average
	 */
	public double varPoisson(double lambda) {
		return lambda;
	}
	/**
	 * This method can be used to find the lower bound for the probability that a random variable is within the interval
	 * @param k - number found after getting the within number from a data set and dividing by the standard deviation
	 * @param greaterThan - True if P(|Y − μ| < kσ ) ≥ 1 − 1/k^2
	 * @return result - depending on the equation used, you will get a result based on that formula
	 */
	public double TchebysheffTheorem(double k, boolean greaterThan){
		double result;
		if (greaterThan) {
			result = 1-(1/Math.pow(k,2));
			return result;
		}
		else {
			result = 1/Math.pow(k,2);
			return result;
		}
	}
	/**
	 * The test cases for each method that uses the sample arrays initialized at start of program and numbers I inputed from homework problems
	 */
	public void testCases() {
		StatsLibrary2 test = new StatsLibrary2();
		System.out.println("The poisson distribution is: " + test.poissonDist(6, 5));
		System.out.println("The expected poisson distribution is: " + test.expectedPoisson(6));
		System.out.println("The variance for poisson distribution is: " + test.varPoisson(6));
		
		System.out.println("The Tchebysheff Theorem is: " + test.TchebysheffTheorem(2, true));
		System.out.println("The Tchebysheff Theorem is: " + test.TchebysheffTheorem(2, false));
	}
}
