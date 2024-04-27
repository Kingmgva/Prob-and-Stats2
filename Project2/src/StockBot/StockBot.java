package StockBot;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
/**
 * This class is created to get the MA trend line and RSI. Through those two values we use it to determine the buy and sell points in stocks. We want to use those values check stock data, figure out whether it is on the rise, downhill, or steady and depending on what it returns we give advise to user to buy, sell, or do nothing. At the end we calculate total net worth after a year.
 * @author Melvin Vazquez
 *
 */
public class StockBot {
	//These Variables are for the MA trend line and RSI formula
	private ArrayList<Double> originalClosingPrice = new ArrayList<Double>();
	private ArrayList<Double> closingPrices = new ArrayList<Double>();
	private ArrayList<Double> closingPricesAverage = new ArrayList<Double>();
	private ArrayList<String> weeklyDates = new ArrayList<String>();
	private ArrayList<Double> RSIValues = new ArrayList<Double>();
	private double avgU = 0;
	private double avgD = 0;
	private double RS;
	private double RSI;
	private int windowVal = 4;
	private int smoothingTimes = 1;
	private int nPeriodWeekly = 14;
	
	//These Variables are for the user and stockbot
	private double balance;
	private int stockShares;
	
	
	public StockBot(double initalBalance) {
		
	}
	public double loadedData() {
		return 1;
	}
	public int tradeEvaluator() {
		return 0;
	}
	/**
	 * This gets the RSI value after relative strength is found out. We add that value to the RSIValues ArrayList and then call RSIGraph method
	 */
	public void relativeStrengthIndex() {
		RSI = 100 - 100/(1+RS);
		RSIValues.add(RSI);
		RSIGraph();
	}
	/**
	 * This calculates the average up and down in a period set at the top of the code, after we get the result and add it to either up and down we divide by the period for each and call the relativeStrength() method
	 */
	public void averageDownAndUp() {
		double up = 0;
		double down = 0;
		double result;
		for(int i = 1; i<=nPeriodWeekly; i++){
			result = closingPrices.get(i) - closingPrices.get(i-1);
			if(result > 0) {
				up += result;
			}
			else if(result < 0){
				down += Math.abs(result);
			}
		}
		avgU = up/nPeriodWeekly;
		avgD = down/nPeriodWeekly;
		System.out.println(avgU);
		System.out.println(avgD);
		relativeStrength();
	}
	/**
	 * This method checks that avgD isn't 0 and if not then it divided avgU with avgD and gets the RS value. This method then calls the relativeStrengthIndex() method
	 */
	public void relativeStrength() {
		if(avgD != 0) {
			 RS = avgU / avgD;
			 relativeStrengthIndex();
		}
	}
	/**
	 * This puts the RSI value into a csv and the dates that were looked at during that time 
	 */
	public void RSIGraph() {
		try {
			File csvFile = new File("RSI.csv");
			PrintWriter out = new PrintWriter(csvFile);
			out.println("Weekly, Relative Strength Index");
			for(int i=0; i<RSIValues.size();i++) {
				out.printf("%s, %f\n", weeklyDates.get(i), RSIValues.get(i));
			}
		out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	/**
	 * This is a copy from my salter/smoothing classes that gets the path of the stock csv and sets values to its corresponding ArrayList. At the end this method puts the original closing price inside a different ArrayList since the other one will be used in smoothingData() and will be edited. Calls smoothing CSV at the end
	 */
	public void getData() {
		String path = "C://Users//melvi//eclipse-workspace//Git//Prob-and-Stats2//Project2//src//StockBot//AMDWeeklyStock.csv/";
		String line = "";
		try {
			BufferedReader reader = new BufferedReader(new FileReader(path));
			while((line = reader.readLine())!= null){
				String[] vals = line.split(",");
				try {
					String date = vals[0];
					double closingPrice = Double.parseDouble(vals[4]);
					closingPrices.add(closingPrice);
					weeklyDates.add(date);
				}
				catch(NumberFormatException e){
					System.err.println("Error parsing closing price: " + e.getMessage()); 
				}
			}
			originalClosingPrice.addAll(closingPrices);
			smoothingData();
			smoothingCSV();
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * This is the same code as my smoothing class for smoothing the data, the only difference is the names of the ArrayList sizes. We just changed the variables from  smoothing class to the variables pertaining to this class 
	 */
	public void smoothingData(){
		try {
			for(int i=0; i<smoothingTimes; i++) {
				for(int j = 0; j<closingPrices.size();j++){
					double avgClosingPrice = 0;
					int count = 0;
					for (int k = j-windowVal; k<=j+windowVal; k++) {
						if(k >= 0 && k < closingPrices.size()) {
							avgClosingPrice += closingPrices.get(k);
							count++;
						}
					}
					closingPricesAverage.add(avgClosingPrice/count);
				}
				closingPrices.clear();
				closingPrices.addAll(closingPricesAverage);
				closingPricesAverage.clear();
			}
		}
		catch(ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
		}
	}
	/**
	 * This gets the average trend line by using the smoothed closing price data and originalClosing price data to see where the trend is moving in that period
	 */
	public void smoothingCSV() {
		try {
			File csvFile = new File("averageTrendLine.csv");
			PrintWriter out = new PrintWriter(csvFile);
			out.println("Weekly, Original Closing Price, Average Closing Price");
			for(int i=0; i<closingPrices.size();i++) {
				out.printf("%s, %f, %f\n", weeklyDates.get(i) ,originalClosingPrice.get(i), closingPrices.get(i));
			}
		out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	/**
	 * This organizes the code a bit more and runs the programs in the order I want
	 */
	public void run() {
		getData();
		averageDownAndUp();
		
	}
}
