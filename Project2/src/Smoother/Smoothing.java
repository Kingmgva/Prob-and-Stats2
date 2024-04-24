package Smoother;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Smoothing {
	private ArrayList<Double> xPoints = new ArrayList<Double>();
	private ArrayList<Double> yPoints = new ArrayList<Double>();
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
					System.err.println("Error parsing x or y value: " + e.getMessage()); // Error handling
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
}
