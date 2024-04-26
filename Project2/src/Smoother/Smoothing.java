package Smoother;
/**
 * This method grabs the salted data from the Salter class and gets the average of the y values around the index we are looking at to smooth the messed up data and fix it as accurate as possible to the original data values.
 * 
 * @author Melvin Vazquez
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Smoothing {
	/**
	 * All the variables and arraylists I used throughout the class
	 */
	private ArrayList<Double> xPoints = new ArrayList<Double>();
	private ArrayList<Double> yPoints = new ArrayList<Double>();
	private ArrayList<Double> avgYPoints = new ArrayList<Double>();
	private int num;
	private int smootherNumOfTimes;
	Scanner userInput = new Scanner(System.in);
	/**
	 * This method is used to grab the csv file from the salter class and input the values into the two arraylists that I made for the x and y values.
	 */
	public void getData() {
		String path = "C://Users//melvi//eclipse-workspace//Git//Prob-and-Stats2//Project2//saltedGraph.csv/";
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
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * This method use a triple for loop to get the y points inside the messed up data and find the average from the surrounding y values and put it in the arraylist for the average found. We do that for all the values and after we finish we replace the values of the messed up data with the smoothed ones. Then we empty out the arraylist holding the average of the points. After that depending on how many times we have to smooth the data, we repeat the cycle.
	 * @param windowVal - the number of iterations we go left and right from the index we are looking at 
	 * @param smoothingTimes - the amount of times we smooth the data 
	 */
	public void smoothingData(int windowVal, int smoothingTimes){
		try {
			for(int i=0; i<smoothingTimes; i++) {
				for(int j = 0; j<yPoints.size();j++){
					double y = 0;
					int count = 0;
					for (int k = j-windowVal; k<=j+windowVal; k++) {
						if(k >= 0 && k < yPoints.size()) {
							y += yPoints.get(k);
							count++;
						}
					}
					avgYPoints.add(y/count);
				}
				yPoints.clear();
				yPoints.addAll(avgYPoints);
				avgYPoints.clear();
			}
		}
		catch(ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
		}
		smoothingCSV();
	}
	/**
	 * This method gets the user input for how many values they would like to iterate to left and right from index we are looking at and how many times they want to smooth the data. Handles misinputs in case a number is not inputted. Then it calls the smoothing data method.
	 */
	public void setRangeAndTime() {
		boolean dataGotten = false;
		System.out.println("Please set the window value and number of times you would like to smooth the data");
		while(!dataGotten) {
			try {
				System.out.println("Window Value: ");
				num = userInput.nextInt();
				System.out.println("Number of times you would like to smooth the data: ");
				smootherNumOfTimes = userInput.nextInt();
				dataGotten = true;
			}
			catch(InputMismatchException e) {
				System.out.println("Inputs must be a number");
				dataGotten = false;
				userInput.nextLine();
			}
		}
		smoothingData(num, smootherNumOfTimes);		
	}
	/**
	 * This method grabs the finished smoothed y points and inputs it into a new csv file with the x points from the salter class, which has not changed from the original
	 */
	public void smoothingCSV() {
		try {
			File csvFile = new File("smoothedGraph.csv");
			PrintWriter out = new PrintWriter(csvFile);
			out.println("X-Values, Y-Values");
			for(int i=0; i<xPoints.size();i++) {
				out.printf("%f, %f\n", xPoints.get(i), yPoints.get(i));
			}
		out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	/**
	 * This method is just an organizer that calls the methods in the order it is supposed to be called in, makes it easier in the tester class.
	 */
	public void run() {
		getData();
		setRangeAndTime();
	}
}
