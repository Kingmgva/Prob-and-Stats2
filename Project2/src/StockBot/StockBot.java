package StockBot;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

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
	public void relativeStrengthIndex() {
		RSI = 100 - 100/(1+RS);
		RSIValues.add(RSI);
		RSIGraph();
	}
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
	public void relativeStrength() {
		if(avgD != 0) {
			 RS = avgU / avgD;
			 relativeStrengthIndex();
		}
	}
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
	public void run() {
		getData();
		averageDownAndUp();
		
	}
}
