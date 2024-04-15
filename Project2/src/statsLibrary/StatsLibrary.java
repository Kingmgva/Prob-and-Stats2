package statsLibrary;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
/**
 * This class is built up of methods that implement formulas found in statistics like mean, median, and mode.
 * It also finds probability cases and uses many theorems like Bayes rule, binomial, geometric distribution, and
 * other formulas.
 * 
 * @author Melvin Vazquez
 */

public class StatsLibrary {
	/**
	 * initializes local array variables with a list of numbers to be used throughout the program
	 */
	private int [] sampleNum = {1,2,3,4,5,6,7,8,8,8,9,9,8};
	private double [] sampleNum2 = {2,5,4,1,7,0,8,9,5,2};
	public StatsLibrary() {

	}

	public StatsLibrary(int[] sampleArr) {

	}
	/**
	 * This method finds the mean of an array by adding up all the numbers inside the array and then dividing by the length of the array
	 * 
	 * @param: sampleArray, takes an array of integers to complete method
	 * @return: result of the sum divided by the length of array
	 */
	public double findMean(int[] sampleArray) {
		double sum = 0;
		for (int i = 0; i < sampleArray.length; i++) {
			sum = sampleArray[i] + sum;
		}
		double result = sum / sampleArray.length;
		return result;
	}

	/**
	 * This method finds the median of an array by sorting the array first, and then depending on whether length is even or odd, it will take different actions. If odd the length will divide by 2 and use that number to get the midNum, otherwise it will take the two middle numbers, add them up, and divide by 2 to find the middle number.
	 * 
	 * @param: sampleArr - takes an array of numbers to find median
	 * @return: midNum - which is the number found after completing formula and finding the middle most number of the array
	 */
	public double findMedian(double[] sampleArr) {
		Arrays.sort(sampleArr); //sorts the list of numbers
		double midNum = 0;
		if (sampleArr.length % 2 == 0) { //checks length of array and whether it is even or odd
			midNum = (sampleArr[sampleArr.length / 2] + sampleArr[sampleArr.length / 2 - 1]) / 2;
			return midNum;
		} else {
			return midNum = sampleArr[(sampleArr.length / 2)];
		}
	}

	/**
	 * This method finds the mode of an array by checking each number with each other and finding which number has the most occurrences inside the array. The number with the most occurrences is returned, otherwise null is returned
	 * 
	 * @param: sampleArr - takes an array of numbers to be used in method
	 * @return: uniqueNum - the number that has the most occurrences in the array 
	 * @return: null - if there is no unique number or more than one, it will return null
	 */
	public Integer findMode(int[] sampleArr) {
		int count = 0;
		int maxCount = 0;
		Integer uniqueNum = 0;
		for (int i = 0; i < sampleArr.length; i++) {
			count = 0;
			for (int j = i+1; j < sampleArr.length; j++) {
				if (sampleArr[i] == sampleArr[j]) {
					count++;
				}
			}
			if (count > maxCount){
				maxCount = count;
				uniqueNum = sampleArr[i];
			}
			else if (count == maxCount) {
				uniqueNum = null;
			}
		}
		return uniqueNum;
	}

	/**
	 * This method finds the standard deviation of the array through calling the mean method and using the formula provided in the stats textbook
	 * 
	 * @param: userInput - which is the array of numbers the user himself input
	 * @return: standardDeviation - the number found after completing the formula
	 */
	public double standardDeviation(int[] sampleArr) {
		//array list to store the data found after completing the first iteration
		ArrayList<Double> data = new ArrayList<Double>();
		//setting variables to find num result, what I will return at the end, and the mean results
		double numResult = 0;
		double standardDeviation = 0;
		double meanResult = findMean(sampleArr);
		//This for loop goes through the length of the array and it subtracts each integer in array by mean result
		for (int i = 0; i < sampleArr.length; i++) {
			numResult = sampleArr[i] - meanResult;
			//after finding the first result, it will square the result, this results in the variance
			numResult = numResult * numResult;
			//First iteration is done and will add the final result to the data list
			data.add(numResult);
		}
		//This for loop adds up all the numbers inside the data array list
		for (int j = 0; j < data.size(); j++) {
			numResult = numResult + data.get(j);
		}
		//This is the final iteration in which the number result will divide by the total indexes of size and sets it
		//equal to standardDeviation
		standardDeviation = numResult / (data.size() - 1);
		//We usestandardDeviation and square root that to find the final answer
		standardDeviation = Math.sqrt(standardDeviation);
		//this line just returns the answer we get, which is the standardDeviation
		return standardDeviation;
	}
	/**
	 * This method uses double data type to find factorial which is multiplying the number iteratively while subtracting one each time. ex: 5*4*3*2*1
	 * @param num - the number that is put into the factorial
	 * @return numFact - returns the result of num*num-1*num-2*...*num-n using double data type.
	 */
	public double factorial(double num){
		double numFact = num;
		for (double i = numFact - 1; i > 0; i--) {
			numFact = numFact*i;
		}
		return numFact;
	}
	/**
	 * This method uses long data type to find factorial which is multiplying the number iteratively while subtracting one each time. ex: 5*4*3*2*1
	 * @param num - the number that is put into the factorial
	 * @return numFact - returns the result of num*num-1*num-2*...*num-n using long data type.
	 */
	public long longFactorial(long num){
		long numFact = num;
		for (long i = numFact - 1; i > 0; i--) {
			numFact = numFact*i;
		}
		return numFact;
	}
	/**
	 * This method uses bigInteger data type to find factorial which is multiplying the number iteratively while subtracting one each time. ex: 5*4*3*2*1
	 * @param num - the number that is put into the factorial
	 * @return numFact - returns the result of num*num-1*num-2*...*num-n using big integer data type.
	 */
	public BigInteger bigFactorial(int num){
		BigInteger numFact = new BigInteger("1");
		numFact = BigInteger.valueOf(num);
		for (int i = numFact.intValue() - 1; i > 0; i--) {
			int numFactNum = numFact.intValue()* i;
			numFact = BigInteger.valueOf(numFactNum);
		}
		return numFact;
	}
	/**
	 * This method uses the double data type to find the combination of ordered objects with size of subset and implements the matching data type factorial method. 
	 * @param numOfElements - number of objects and this number is put into the factorial method that matches data type being returned 
	 * @param subSetSize - size of the subSet, which is also put into a factorial method of matching data type
	 * @return combinations - returns result of doing factorial(numOfElements)/(factorial(subSetSize)*(factorial(numOfElements-subSetSize)))
	 */
	public double findCombinations(int numOfElements, int subSetSize){
		double combinations;
		combinations = factorial(numOfElements)/(factorial(subSetSize)*(factorial(numOfElements-subSetSize)));
		return combinations;
	}
	/**
	 * This method uses the long data type to find the combination of ordered objects with size of subset and implements the matching data type factorial method. 
	 * @param numOfElements - number of objects and this number is put into the factorial method that matches data type being returned 
	 * @param subSetSize - size of the subSet, which is also put into a factorial method of matching data type
	 * @return combinations - returns result of doing factorial(numOfElements)/(factorial(subSetSize)*(factorial(numOfElements-subSetSize)))
	 */
	public long longCombinations(int numOfElements, int subSetSize){
		long combinations;
		combinations = longFactorial(numOfElements)/(longFactorial(subSetSize)*(longFactorial(numOfElements-subSetSize)));
		return combinations;
	}
	/**
	 * This method uses the bigInteger data type to find the combination of ordered objects with size of subset and implements the matching data type factorial method. 
	 * @param numOfElements - number of objects and this number is put into the factorial method that matches data type being returned 
	 * @param subSetSize - size of the subSet, which is also put into a factorial method of matching data type
	 * @return combinations - returns result of doing factorial(numOfElements)/(factorial(subSetSize)*(factorial(numOfElements-subSetSize)))
	 */
	public BigInteger bigCombinations(int numOfElements, int subSetSize){
		BigInteger combinations = new BigInteger("1");
		combinations = bigFactorial(numOfElements).divide(((bigFactorial(subSetSize)).multiply((bigFactorial(numOfElements-subSetSize)))));
		return combinations;
	}
	
	/**
	 * This method uses long data type to find the permutation of number of objects and the number of ways to order distinct objects. Implements matching data type factorial method.
	 * @param numOfElements - number of objects 
	 * @param subSetSize - number of ways of ordering distinct objects 
	 * @return longPermutations - returns result of doing factorial(numOfElements)/(factorial(numOfElements-subSetSize))
	 */
	public long longPermutations(int numOfElements, int subSetSize) {
		long longPermutations;
		longPermutations = longFactorial(numOfElements)/(longFactorial(numOfElements-subSetSize));
		return longPermutations;
	}
	/**
	 * This method uses big integer data type to find the permutation of number of objects and the number of ways to order distinct objects. Implements matching data type factorial method.
	 * @param numOfElements - number of objects 
	 * @param subSetSize - number of ways of ordering distinct objects 
	 * @return bigPermutations - returns result of doing factorial(numOfElements)/(factorial(numOfElements-subSetSize))
	 */
	public BigInteger bigPermutations(int numOfElements, int subSetSize) {
		BigInteger bigPermutations = new BigInteger("1");
		bigPermutations = bigFactorial(numOfElements).divide(bigFactorial(numOfElements-subSetSize));
		return bigPermutations;
	}
	/**
	 * This method uses double data type to find the permutation of number of objects and the number of ways to order distinct objects. Implements matching data type factorial method.
	 * @param numOfElements - number of objects 
	 * @param subSetSize - number of ways of ordering distinct objects 
	 * @return permutations - returns result of doing factorial(numOfElements)/(factorial(numOfElements-subSetSize))
	 */
	public double findPermutations(int numOfElements, int subSetSize){
		double permutations;
		permutations = factorial(numOfElements)/factorial(numOfElements - subSetSize);
		return permutations;
	}
	/**
	 * This method finds the conditional probability of event A and B intersected and dividing that by the probability of event A to find probability of A given B or vice versa.
	 * @param probA - probability of event A
	 * @param probB - probability of event B
	 * @param probAB - probability of intersection event A and B
	 * @return probAgivenB - returns probability of event A given event B by doing probAB/probB
	 */
	public double conditionalProb(double probA, double probB, double probAB){
		if(probB > 0) {
			double probAgivenB = probAB / probB;
			return probAgivenB;
		}
		else{
			throw new ArithmeticException();
		}
	}
	/**
	 * This method checks whether the two events are dependent or independent by checking each condition 
	 * @param probA - probability of event A
	 * @param probB - probability of event A
	 * @param probAB - probability of event A and B
	 * @return independent/dependent - implements conditionalProb method to check if the events are dependent or independent
	 */
	public String independent(double probA, double probB, double probAB) {
		if(conditionalProb(probA,probB,probAB)==probA) {
			return "independent";
		}
		if(conditionalProb(probB,probA,probAB)==probB){
			return "independent";
		}
		if(probAB == probB*probA) {
			return "independent";
		}
		else {
			return "dependent";
		}
	}
	/**
	 * This method finds the probability of intersection of Event A and B, but first checks the dependency by calling the independent method. A different formula is used if dependency ends up being independent or dependent
	 * @param probA - probability of event A
	 * @param probB - probability of event A
	 * @param probAB - probability of event A and B
	 * @return probAinterB - probability of intersection of event A and B.
	 */
	public double multiplicativeLaw(double probA, double probB, double probAB){
		String dependency;
		dependency = independent(probA, probB, probAB);
		double probAinterB;
		//checks whether dependency is independent and runds this section if it is
		if(dependency == "independent") 
		{
			//implements conditionalProb and multiplies that by probA
			probAinterB = probA*conditionalProb(probB,probA,probAB); 
			return probAinterB;
		}
		else{
			probAinterB = probA * probB;
			return probAinterB;
		}
	}
	/**
	 * This method returns the probability of union of events A and B. if mutually exclusive a different formula is used.
	 * @param probA - probability of event A
	 * @param probB - probability of event A
	 * @param probAB - probability of event A and B
	 * @param exclusive - whether event A and B are mutually exclusive events 
	 * @return probAunionB - if mutually exclusive the probability of event A and probability of event B are added together. If not then the same thing happens but at the end the sum is subtracted by probAB
	 */
	public double additiveLaw(double probA, double probB, double probAB, boolean exclusive){
		double probAunionB;
		if(!exclusive) {
			probAunionB = probA + probB - probAB;
			return probAunionB;
		}
		else {
			probAB = 0;
			probAunionB = probA + probB;
			return probAunionB;
		}
	}
	/**
	 * This method implements conditional probability to get the probability of A given B partition and multiplying that by the probability of the partition. This returns the total probability of event A
	 * @param probA - probability of event A
	 * @param probBi - partition of a sample space 
	 * @param probAB - probability of event A and B
	 * @return totalProbA - returns a result of implementing conditional probability method and inputing the parameters into it and then multiplying that result by probBi.
	 */
	public double totalProb(double probA, double probBi, double probAB) {
		double totalProbA = conditionalProb(probA,probBi,probAB) * probBi;
		return totalProbA;
	}
	/**
	 * This method gets the probability of B partition j given event A. implements the totalProb method 
	 * @param probA - probability of event A
	 * @param probBi - probability of event B partition i
	 * @param probBj - probability of event B partition j
	 * @param probAB - probability of event B
	 * @return probBjGivenA - returns the results of totalProb(probA, probBj, probAB)/totalProb(probA, probBi, probAB) 
	 */
	public double bayesRule(double probA, double probBi, double probBj, double probAB) {
		double probBjGivenA;
		probBjGivenA = totalProb(probA, probBj, probAB)/totalProb(probA, probBi, probAB);
		return probBjGivenA;
	}
	/**
	 * This method returns the binomial distribution of probability of y, which is the number of successes during the trials. Implements bigCombinations method in the formula.
	 * @param nTrials - fixed number of identical trials
	 * @param yGoal - number of successes observed 
	 * @param success - percentage of success
	 * @return finalRes - returns the results of completed run of the formula
	 */
	public double binomialDist(int nTrials,int yGoal,double success) {
		//we get the fail percentage by subtracting success by 1
		double fail = 1.0 - success;
		/*
		 * initializes first part of formula to binomialResult which calls the combinations method
		 * bigCombinations(nTrials, yGoal);
		 */
		BigInteger binomialResult = bigCombinations(nTrials, yGoal);
		/*
		 * use double and convert bigInteger to double and rest of formula and set it to finalRes
		 * binomialResult.doubleValue()*Math.pow(success, yGoal)*Math.pow(fail,nTrials-yGoal)
		 */
		double finalRes = binomialResult.doubleValue()*Math.pow(success, yGoal)*Math.pow(fail,nTrials-yGoal);
		return finalRes;
	}
	/**
	 * This finds the expected value of the binomial distribution which is the mean. 
	 * @param nTrials - fixed number of identical trials
	 * @param success - percentage of success
	 * @return mean - returns nTrials * success
	 */
	public double expectedBinomial(int nTrials, double success) {
		double mean = nTrials*success;
		return mean;
	}
	/**
	 * This method finds the variance of Binomial distribution, which is also standard deviation
	 * @param nTrials - fixed number of identical trials
	 * @param success - percentage of success
	 * @return variance - nTrials*success*(1-success)
	 */
	public double varianceBinomial(int nTrials, double success) {
		double variance = nTrials*success*(1-success);
		return variance;
	}
	/**
	 * This method is finding the geometric distribution of which number trial the first success was on
	 * @param yGoal - number trial on which first success occurs
	 * @param success - percentage of success
	 * @return - geometricRes - returns the results of Math.pow(1-success, yGoal-1)*success
	 */
	public double geometricDist(int yGoal,double success) {
		double geometricRes = Math.pow(1-success, yGoal-1)*success;
		return geometricRes;
	}
	/**
	 * This method is finding the expected value from geometric distribution, which is the mean
	 * @param yGoal - number trial on which first success occurs
	 * @param success - percentage of success
	 * @return expected - 1/success
	 */
	public double expectedGeometric(int yGoal,double success) {
		double expected = 1/success;
		return expected;
	}
	/**
	 * This method is finding the variance value from geometric distribution, which is the standard deviation
	 * @param yGoal - number trial on which first success occurs
	 * @param success - percentage of success
	 * @return variance - (1-success)/Math.pow(success, 2);
	 */
	public double varGeometric(int yGoal,double success) {
		double variance = (1-success)/Math.pow(success, 2);
		return variance;
	}
	/**
	 * This method is getting the hypergeometric distribution which is trying to find the probability that y contains value from sample. Implements combination method.
	 * @param N - total number of what we have
	 * @param n - the number that we are taking
	 * @param r - number we want 
	 * @param y - number we pick
	 * @return - finalResult - after formula is complete we get the probability of y of the number we want.
	 */
	public double hyperGeometricDist(int N, int n, int r, int y) {
		/*
		 * making three variables because we are multiplying two numbers in the numerator and dividing by the number in the denominator. Each number is using combinations and big integer data type
		 * BigInteger numerator1 = bigCombinations(r,y);
		BigInteger numerator2 = bigCombinations(N-r,n-y);
		BigInteger denominator = bigCombinations(N,n);
		 */
		BigInteger numerator1 = bigCombinations(r,y);
		BigInteger numerator2 = bigCombinations(N-r,n-y);
		BigInteger denominator = bigCombinations(N,n);
		/*
		 * calculate result by converting the big integer to a double and multiplying the two numerators and dividing that by denominator.
		 */
		double finalResult = (numerator1.doubleValue()*numerator2.doubleValue())/denominator.doubleValue();
		return finalResult;
	}
	/**
	 * This method is getting the expected value from HGD, which is the mean value
	 * @param n - the number that we are taking
	 * @param r - number we want 
	 * @param N - total number of what we have
	 * @return result - the mean value we get from(n*r)/(double)N;
	 */
	public double getExpectedHGD(int n, int r, int N) {
		double result = (n*r)/(double)N;
		return result;
	}
	/**
	 * This method gets the variance value from HGD 
	 * @param N - total number of what we have
	 * @param n - the number that we are taking
	 * @param r - number we want 
	 * @return result - variance number we get from n*(r/(double)n)*((N-r)/(double)N)*((N-n)/(double)N-1);
	 */
	public double getVarHGD(int N, int n, int r) {
		double result = n*(r/(double)n)*((N-r)/(double)N)*((N-n)/(double)N-1);
		return result;
	}
	/**
	 * This method gets the negative binomial distribution in which the probability of success being the same from trial to trial is found 
	 * @param y - probability success stays the same trial to trial
	 * @param r - where the success occurs
	 * @param p - percentage of success
	 * @return finalResult - returns the result from completing the formula of bigCombinations(y-1,r-1)*Math.pow(p, r)*Math.pow(qFail, y-r);
	 */
	public double negativeBinomialDistribution(int y, int r, double p) {
		/*
		 * get percentage of fail by subtracting success by 1
		 */
		double qFail = 1-p;
		/*
		 * using combinations method to find the first part of formula
		 */
		BigInteger result = bigCombinations(y-1,r-1);
		/*
		 * finishing the formula and converting to the data type double
		 */
		double finalResult = result.doubleValue()*Math.pow(p, r)*Math.pow(qFail, y-r);
		return finalResult;
		
	}
	/**
	 * This method is getting the expected value from NBD which is the mean
	 * @param y - probability success stays the same trial to trial
	 * @param r - where the success occurs
	 * @param p - percentage of success
	 * @return result - returns the value from r/(double) p
	 */
	public double getExpectedNBD(int y, int r, double p) {
		double result = r/(double) p;
		return result;
	}
	/**
	 * This method is getting the variance value from NBD which is the standard deviation
	 * @param y - probability success stays the same trial to trial
	 * @param r - where the success occurs
	 * @param p - percentage of success
	 * @return result - returns the value from (r*(1-p)/Math.pow(p,2))
	 */
	public double getVarNBD(int y, int r, double p) {
		double result = (r*(1-p)/Math.pow(p,2));
		return result;
	}
	/**
	 * This method finds the probability of events happening a certain amount of time, We use the information given which is the average, lambda, and y. We plug that into the formula. That formula results in the probability.
	 * @param lambda - rate or the average of an event happening
	 * @param y - number of what we want to find probability of 
	 * @return result - Use the rate, which is lambda, to input into formula and find numerator and denominator, which then results in an answer from numerator/denominator 
	 */
	public double poissonDist(double lambda, double y) {
		double numerator = (Math.pow(lambda, y))*Math.pow(Math.E,-lambda);
		double denominator = factorial(y);
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
		StatsLibrary test = new StatsLibrary(); 
		System.out.println("The average is: " + test.findMean(sampleNum));
		System.out.println("The median is: " + test.findMedian(sampleNum2));
		System.out.println("The mode is: " + test.findMode(sampleNum));
		System.out.println("The standard Deviation is: " + test.standardDeviation(sampleNum));
		System.out.println("The combination using double is: " + test.findCombinations(12,2));
		System.out.println("The combination using long is: " + test.longCombinations(12,2));
		System.out.println("The combination using big integer is: " + test.bigCombinations(12,2));
		System.out.println("The permutation is: " + test.findPermutations(6,2));
		System.out.println("The permutation using long is: " + test.longPermutations(6,2));
		System.out.println("The permutation using big integer is: " + test.bigPermutations(6,2));
		System.out.println("The factorial is: " + test.factorial(8));
		System.out.println("The factorial using long is: " + test.longFactorial(8));
		System.out.println("The factorial using big integer is: " + test.bigFactorial(8));
		
		System.out.println("The conditional probability: " + test.conditionalProb(8,5,5));
		System.out.println("The independency is: " + test.independent(8,5,5));
		System.out.println("The multiplicative law is: " + test.multiplicativeLaw(8,5,5));
		System.out.println("The additive law is: " + test.additiveLaw(8,5,5,true));
		System.out.println("The total Probability is: " + test.totalProb(8,5,5));
		System.out.println("The bayes rule is: " + test.bayesRule(8,5,5,5));
		
		System.out.println("The binomial distribution is: " + test.binomialDist(5,3,.75));
		System.out.println("The expected binomial distribution: " + test.expectedBinomial(5,.75));
		System.out.println("The variance for binomial distribution is: " + test.varianceBinomial(5,.75));
		
		System.out.println("The geometric distribution is: " + test.geometricDist(4,.5));
		System.out.println("The expected geometric distribution: " + test.expectedGeometric(4,.5));
		System.out.println("The variance for geometric distribution is: " + test.varGeometric(4,.5));
		
		System.out.println("The hypergeometric distribution is: " + test.hyperGeometricDist(10,5,6,4));
		System.out.println("The expected hypergeomtric distribution: " + test.getExpectedHGD(5,6,10));
		System.out.println("The variance for hypergeometric distribution is: " + test.getVarHGD(10,5,6));
		
		System.out.println("The negative binomial distribution is: " + test.negativeBinomialDistribution(5,3,.2));
		System.out.println("The expected negative binomial distribution: " + test.getExpectedNBD(5,3,.2));
		System.out.println("The variance for negative binomial distribution is: " + test.getVarNBD(5,3,.2));
		
		System.out.println("The poisson distribution is: " + test.poissonDist(6, 5));
		System.out.println("The expected poisson distribution is: " + test.expectedPoisson(6));
		System.out.println("The variance for poisson distribution is: " + test.varPoisson(6));
		
		System.out.println("The Tchebysheff Theorem is: " + test.TchebysheffTheorem(2, true));
		System.out.println("The Tchebysheff Theorem is: " + test.TchebysheffTheorem(2, false));
		
	}
}
