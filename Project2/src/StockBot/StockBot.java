package StockBot;
import java.util.Arrays;

public class StockBot {
	private double[]closePrices;
	private int AvgU = 0;
	private int AvgD = 0;
	private double RS;
	double RSI;
	
	public void relativeStrengthIndex() {
		RSI = 100 - 100/(1+RS);
	}
	public void averageDownAndUp(int nPeriod) {
		for(int i = 0; i<=nPeriod; i++){
			for(int j = i+1; j<=nPeriod; j++) {
				if((closePrices[j] - closePrices[i])<0) {
					AvgU++;
				}
				else if((j-i)==0) {
				}
				else {
					AvgD++;
				}
			}
		}
	}
	public void relativeStrength() {
		 RS = AvgU / AvgD;
	}
}
