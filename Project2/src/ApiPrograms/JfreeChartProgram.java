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

/**
 * This class uses JfreeCharts as a way to create a frame chart that shows the graph for all three datasets. The datasets shown in the graph is the one for Plotter, Salter, and Smoother.
 * @author Melvin Vazquez
 *
 */
public class JfreeChartProgram{
	private XYSeriesCollection dataset = new XYSeriesCollection();
	private XYSeries plotterData;
	private XYSeries salterData;
	private XYSeries smootherData;
	/**
	 * This method uses the same logic as the one created in the first plotter class in Java, the only difference is the use of a XY data set. After all data is received, we add the series to a XY collection that holds multiple series
	 * @param min - first x to start at
	 * @param max - last x to end at
	 * @param increments - increment to the next x depending on user input
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
	/**
	 * This method salts the data from the plotter dataset and we get the values in each through a for loop and through built in methods. After that we use a randomizer to add/subtract a number in a range given by user. Once loop is finished the series is added to the dataset
	 * @param min - the minimum value they would like the data to be added to or subtracted from
	 * @param max - the maximum value they would like the data to be added to or subtracted from
	 */
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
	/**
	 * This method is used to fix the data and try to fix it to be as close to the original, Again we use XY series and dataset, once we get results we add to the smoother data series and add that series to the collection
	 * @param windowVal - how many values the user wants to visit left and right from index 
	 * @param smoothingTimes - how many times the data gets smoothed
	 */
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
	 * This creates the CSV for the Plotter dataset
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
	/**
	 * This creates the CSV for the Salter dataset
	 */
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
	/**
	 * This method creates the Smoother CSV with the smoother dataset
	 */
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
	 * This program is getting the bounds and range from user input. Got it from my java plotter class.
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
	 * This class is what creates the frame where the Plotter, Salter, and Smoother get graphed and plotted. 
	 */
	public void XYLineChart() {
		 String chartTitle = "API Chart";
		 String xAxisLabel = "X";
		 String yAxisLabel = "Y";
		JFreeChart chart = ChartFactory.createXYLineChart(chartTitle,
		        xAxisLabel, yAxisLabel, dataset, PlotOrientation.VERTICAL, true, true, false);
		ChartFrame frame = new ChartFrame("Plotter, Salter, and Smoother Plot", chart);
		frame.pack();
		frame.setVisible(true);
	}
}