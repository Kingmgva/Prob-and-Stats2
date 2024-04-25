package Smoother;

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
	private ArrayList<Double> xPoints = new ArrayList<Double>();
	private ArrayList<Double> yPoints = new ArrayList<Double>();
	private ArrayList<Double> avgYPoints = new ArrayList<Double>();
	private int num;
	private int smootherNumOfTimes;
	Scanner userInput = new Scanner(System.in);
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
	public void smoothingData(int windowVal, int smoothingTimes){
		try {
			for(int i=0; i<smoothingTimes; i++) {
				double y = 0;
				int count = 0;
				for(int j = 0; j<yPoints.size();j++){
					for (int k = j-windowVal; k<=j+windowVal; k++) {
						if(k > 0 && k < yPoints.size()) {
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
	public void run() {
		getData();
		setRangeAndTime();
	}
}
