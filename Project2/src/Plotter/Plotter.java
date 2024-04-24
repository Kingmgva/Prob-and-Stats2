/**
 * This program is using a formula/function and plots the output into a CSV. It is taking the start and end points of x values and plugging those values into the function, so that we get the y value. Then these values are exported into a CSV and put into excel to graph.
 * @author Melvin Vazquez
 *
 */
package Plotter;
import java.util.ArrayList;
import java.math.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
public class Plotter {
	private ArrayList<Double> xPoints = new ArrayList<Double>();
	private ArrayList <Double> yPoints = new ArrayList<Double>();
	private int constant = 5;
	private int b = 3;
	private int a = 1;
	/**
	 * This is the formula that I am using in, which is the quadratic formula: y = ax^2+bx+c. The results we get from the formula are inputed into the two arraylist. One for x values and one for y values.
	 * @param start - The lower bound x-value that we are starting at
	 * @param end - The higher bound x-value that we are going up to
	 * @param increment - What we are incrementing the x by
	 */
	public void quadraticFormula(double start, double end, double increment){
		double y = 0;
		for(double i = start; i<=end; i += increment) {
			y = (int) (a*Math.pow(i, 2) + b * i + constant); //y= ax^2+bx+c
			xPoints.add(i);
			yPoints.add(y);
		}
	}
	/**
	 * This program is getting the bounds from user input. Used this website to get a better understanding on how to do try-catch and exceptions for this specific case. https://www.w3schools.com/java/java_try_catch.asp
	 */
	public void bounds() {
		double start;
		double end;
		double increment;
		Scanner userInput = new Scanner(System.in);
		boolean valid = false;
		while(!valid) {
			try {
				System.out.println("Please give the lower bound where you want the data to start at: ");
				start = userInput.nextDouble();
				System.out.println("Please give the higher bound where you want the data to end at: ");
				end = userInput.nextDouble();
				System.out.println("Please give how much you would like to increment the data by:  ");
				increment = userInput.nextDouble();
				checkBounds(start, end, increment);
				valid = true;
			}
			catch(Exception e){
				System.out.println("Invalid Input, try again");
				userInput.next();
			}
		}
	}
	/**
	 * This method checks the bounds to see whether the parameters are valid and can be used, So checks whether the start is lower than the end and whether the increment is greater than 0. If the parameters are valid it will call the quadratic formula method and csvPlotter method.
	 * @param start - The lower bound x-value that we are starting at
	 * @param end - The higher bound x-value that we are going up to
	 * @param increment - What we are incrementing the x by
	 */
	public void checkBounds(double start, double end, double increment) {
		if(start > end || increment <= 0) {
			System.out.println("Not a valid interval, Start has to be lower than the end point and should increment by more than 0.");
			bounds();
		}
		else {
			quadraticFormula(start, end, increment);
			System.out.println("Your X points are: " + xPoints + "\nYour Y points are: " + yPoints);
			csvPlotter();
		}
	}
	/**
	 * This method is creating the file that we are inputting the data into and uses printwriter to actually input the data onto that file. So we use the two arraylists that were given values and we use a for loop to get all values inside the arraylists. We use a try catch in the case that the file is not found, but the catch won't occur since the file keeps getting created in the try. Used this video https://www.youtube.com/watch?v=dHZaqMmQNO4&t=73s to learn how to create a File and input data into it.
	 */
	public void csvPlotter() {
		try {
			File csvFile = new File("dataPlotter.csv");
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
}
