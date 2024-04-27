package ApiPrograms;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

import org.apache.commons.math4.legacy.stat.descriptive.DescriptiveStatistics;

public class ApacheAndJfreeProgram{
	private XYSeriesCollection dataset = new XYSeriesCollection();
	private XYSeries plotterData;
	private XYSeries salterData;
	private XYSeries smootherData;
	/**
	 * 
	 * @param min
	 * @param max
	 * @param increments
	 */
	public void plotterAPI(double min, double max, double increments) {
		double y;
		plotterData = new XYSeries("Plotter Data");
		for(double i=min; i<= max; i+= increments) {
			y = Math.pow(i, 2)+(3*i)+5;
			plotterData.add(i,y);
		}
		dataset.addSeries(plotterData);
		plotterCSV();
	}
	public void saltedData(double min, double max) {
		 salterData = new XYSeries("Salted Data");
		 Random random = new Random();
			int count = 0;
			int num;
			do {
				for(int i = 0; i<plotterData.getItemCount();i++) {
					num = random.nextInt(2)+1;
					if(num ==1)
					{
						salterData.add(plotterData.getDataItem(i).getXValue(),plotterData.getDataItem(i).getYValue()+Math.floor(Math.random()*(max-min+1)+min));
					}
					else {
						salterData.add(plotterData.getDataItem(i).getXValue(),plotterData.getDataItem(i).getYValue()-Math.floor(Math.random()*(max-min+1)+min));
					}
					count++;
				}
			}
			while(count != plotterData.getItemCount());
			dataset.addSeries(salterData);
			salterCSV();
	}
	public void smoothedData(int windowVal, int smoothingTimes) {
		smootherData = new XYSeries("Smoothed Data");
		double result;
		try {
			for(int i=0; i<smoothingTimes; i++) {
				for(int j = 0; j<salterData.getItemCount();j++){
					double y = 0;
					int count = 0;
					for (int k = j-windowVal; k<=j+windowVal; k++) {
						if(k >= 0 && k < salterData.getItemCount()) {
							y += salterData.getDataItem(k).getYValue();
							count++;
						}
					}
					result = y/count;
					smootherData.add(salterData.getDataItem(j).getX(),result);
				}
				dataset.addSeries(smootherData);
				smootherCSV();
			}
		}
		catch(ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	 */
	public void plotterCSV() {
		try {
			File csvFile = new File("PlotterAPI.csv");
			PrintWriter out = new PrintWriter(csvFile);
			out.println("X-Values, Y-Values");
			for(int i=0; i<plotterData.getItemCount();i++) {
				out.printf("%f, %f\n", plotterData.getDataItem(i).getXValue(), plotterData.getDataItem(i).getYValue());
		}
		out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	public void salterCSV() {
		try {
			File csvFile = new File("SalterAPI.csv");
			PrintWriter out = new PrintWriter(csvFile);
			out.println("X-Values, Y-Values");
			for(int i=0; i<salterData.getItemCount();i++) {
				out.printf("%f, %f\n", salterData.getDataItem(i).getXValue(), salterData.getDataItem(i).getYValue());
		}	
		out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	public void smootherCSV() {
		try {
			File csvFile = new File("SmootherAPI.csv");
			PrintWriter out = new PrintWriter(csvFile);
			out.println("X-Values, Y-Values");
			for(int i=0; i<smootherData.getItemCount();i++) {
				out.printf("%f, %f\n", smootherData.getDataItem(i).getXValue(), smootherData.getDataItem(i).getYValue());
		}
		out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	/**
	 * This program is getting the bounds from user input. Got it from my java plotter class.
	 */
	public void boundsAndRange() {
		double start;
		double end;
		double increment;
		double max;
		double min;
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
				System.out.println("Please give minimum to add/subtract data to salt it:  ");
				min = userInput.nextDouble();
				System.out.println("Please give maximum to add/subtract data to salt it:  ");
				max = userInput.nextDouble();
				checkBounds(start, end, increment, min, max);
				valid = true;
			}
			catch(Exception e){
				System.out.println("Invalid Input, try again");
				userInput.next();
			}
		}
	}
	/**
	 * This method checks the bounds to see whether the parameters are valid and can be used, So checks whether the start is lower than the end and whether the increment is greater than 0. If the parameters are valid it will call the plotterAPI method. Got it from my java plotter class.
	 * @param start - The lower bound x-value that we are starting at
	 * @param end - The higher bound x-value that we are going up to
	 * @param increment - What we are incrementing the x by
	 */
	public void checkBounds(double start, double end, double increment, double min, double max) {
		if(start > end || increment <= 0 || min >= max) {
			System.out.println("Not a valid interval, Start has to be lower than the end point and should increment by more than 0. Minimum range should not be more than maximum");
			boundsAndRange();
		}
		else {
			plotterAPI(start, end, increment);
			saltedData(min, max);
			smoothedData(5,1);
		}
	}
	/**
	 * 
	 */
	public void XYLineChart() {
		 String chartTitle = "API Chart";
		 String xAxisLabel = "X";
		 String yAxisLabel = "Y";
		JFreeChart chart = ChartFactory.createXYLineChart(chartTitle,
		        xAxisLabel, yAxisLabel, dataset, PlotOrientation.VERTICAL, true, true, false);
		ChartFrame frame = new ChartFrame("PSS Plot", chart);
		frame.pack();
		frame.setVisible(true);
	}
}