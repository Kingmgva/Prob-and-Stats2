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
	private ArrayList<Double> xPoints = new ArrayList<Double>();
	private ArrayList <Double> yPoints = new ArrayList<Double>();
	private ArrayList <Double> newY = new ArrayList<Double>();
	private int min;
	private int max;
	private Scanner userInput = new Scanner(System.in);
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
	public void setArrayList() {
		String path = "C://Users//melvi//eclipse-workspace//Git//Prob-and-Stats2//Project2//dataPlotter.csv/";
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
					System.err.println("Error parsing x or y value: " + e.getMessage()); // Error handling
				}
			}
			setRange();
			messedUpData(yPoints, min, max);
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
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
	public void messedUpData(ArrayList<Double> yVal, double min, double max){
		Random random = new Random();
		int count = 0;
		int num;
		do {
			for(int i = 0; i<yVal.size();i++) {
				num = random.nextInt(2)+1;
				if(num ==1)
				{
					newY.add(yVal.get(i)+Math.floor(Math.random()*(max-min+1)+min));
				}
				else {
					newY.add(yVal.get(i)-Math.floor(Math.random()*(max-min+1)+min));
				}
				count++;
			}
		}
		while(count != yVal.size());
		System.out.println("Y-Values: " + newY);
	}
	public void run() {
		setArrayList();
		saltingGraph();
	}
}
