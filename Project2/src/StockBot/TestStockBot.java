package StockBot;

import static org.junit.Assert.*;

import org.junit.Test;
/**
 * This is the tester for the stockBot
 * @author Melvin Vazquez
 *
 */
public class TestStockBot {

	/**
	 * This runs the program for stockbot and initializes a starting balance.
	 * @param args
	 */
	public static void main(String[]args){
		StockBot test = new StockBot(10000);
		test.run();
	}

}
