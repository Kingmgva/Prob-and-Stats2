/**
 * This program will get the csv file from the plotter class and will loop through the y values and add or subtract a random number from it. Point of this class is to mess up the data.
 * @author Melvin Vazquez
 *
 */
package Salter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
public class Salting {
	/**
	 * All of these are variables that are used throughout the whole class, Have two arraylists to store the original x and y, and then a third arraylist to store the new y values. Have a scanner to get the user input and two data types for int to get the range.
	 */
	private ArrayList<Double> xPoints = new ArrayList<Double>();
	private ArrayList <Double> yPoints = new ArrayList<Double>();
	private ArrayList <Double> newY = new ArrayList<Double>();
	private int min;
	private int max;
	private Scanner userInput = new Scanner(System.in);
	/**
	 * This method is getting the messed up data points and writing it into a new csv file called saltedGraph.csv.
	 */
	public void saltingGraph() {
		try {
			File csvFile = new File("saltedGraph.csv");
			PrintWriter out = new PrintWriter(csvFile);
			out.println("X-Values, Y-Values");
			for(int i=0; i<newY.size();i++) {
				out.printf("%f, %f\n", xPoints.get(i), newY.get(i));
			}
		out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	/**
	 * This method is getting the path of the csv from the plotter class and is being read by the buffered reader. This is then set to a vals value that splits the file by the comma. I then use a try catch to get the string of x and y and convert it to a double through parse. Then that value gets added to the arrayList. The error at the beginning is for the heading, which I could not figure out how to bypass it. So the catch helps the code keep running and not end. After that I call two other methods called setRange and messedUpData.
	 * I followed this video to understand how to get the csv file and read it. https://www.youtube.com/watch?v=-Aud0cDh-J8
	 */
	public void setArrayList() {
		String path = "C://Users//melvi//eclipse-workspace//Git//Prob-and-Stats2//Project2//CSVs//dataPlotter.csv/";
		String line = "";
		try {
			BufferedReader reader = new BufferedReader(new FileReader(path));
			while((line = reader.readLine())!= null){
				String[] vals = line.split(",");
				try {
					double x = Double.parseDouble(vals[0]);
					double y = Double.parseDouble(vals[1]);
					xPoints.add(x);
					yPoints.add(y);
				}
				catch(NumberFormatException e){
					System.err.println("Error parsing x or y value: " + e.getMessage());
				}
			}
			setRange();
			messedUpData(min, max);
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * This method is getting the user input for how big they want the range to be for the randomizer that will mess up the data. So it will ask the user for a min and max, while also checking to make sure the value inputted is an integer and if not the loop will keep going. 
	 * I used the link below to figure out what the catch was for input error. 
	 * https://stackoverflow.com/questions/56523561/how-can-i-make-the-code-catch-the-error-when-the-input-provided-is-not-a-number 
	 */
	public void setRange() {
		boolean rangeSet = false;
		System.out.println("Set a range of min and max to salt the data");
		while(!rangeSet) {
			try {
				System.out.println("Min: ");
				min = userInput.nextInt();
				System.out.println("Max: ");
				max = userInput.nextInt();
				if(min > max || min == max) {
					System.out.println("Try again, min has to be smaller then max");
				}
				else {
					rangeSet = true;
				}
			}
			catch(InputMismatchException e){
				System.out.println("Value must be a number");
				rangeSet = false;
				userInput.nextLine();
			}
		}
	}
	/**
	 * This method has two parameters, the min and max that were set in the setRange method. With these two parameters I did a do while loop that will keep getting a random number from 1-2. If it equals 1 then the y value at that index will be added to from the random number between the min and max. Otherwise it will subtract. The counter is there to make sure that there is a condition to exit the while loop and isn't infinite. 
	 * I followed two websites in the creation of this code, one I used so that I understood the format for do while and the other I used to figure out how to get the random number that is inclusive of max and min.
	 * https://www.geeksforgeeks.org/java-do-while-loop-with-examples/
	https://www.educative.io/answers/how-to-generate-random-numbers-in-java
	 * 
	 * @param min - the minimum number that should be added/subtracted from the y value
	 * @param max - the maximum number that should be added/subtracted from the y value
	 */
	public void messedUpData(double min, double max){
		Random random = new Random();
		int count = 0;
		int num;
		do {
			for(int i = 0; i<yPoints.size();i++) {
				num = random.nextInt(2)+1;
				if(num ==1)
				{
					newY.add(yPoints.get(i)+Math.floor(Math.random()*(max-min+1)+min));
				}
				else {
					newY.add(yPoints.get(i)-Math.floor(Math.random()*(max-min+1)+min));
				}
				count++;
			}
		}
		while(count != yPoints.size());
		System.out.println("Y-Values: " + newY);
	}
	/**
	 * This is a run method that calls the methods needed to finish the code fully and to make it more organized.
	 */
	public void run() {
		setArrayList();
		saltingGraph();
	}
}
