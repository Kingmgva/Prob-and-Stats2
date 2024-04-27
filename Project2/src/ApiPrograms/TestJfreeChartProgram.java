package ApiPrograms;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestJfreeChartProgram {

	@Test
	public static void main(String[]args) {
		JfreeChartProgram test = new JfreeChartProgram();
		test.boundsAndRange();
		test.XYLineChart();
	}

}
