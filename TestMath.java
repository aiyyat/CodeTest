package zzTest;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

import org.apache.commons.math3.stat.regression.SimpleRegression;
import org.junit.Test;

import junit.framework.TestCase;

public class TestMath {
	@Test
	public void testAverage() {
		Double[] d = { 4.8, 4.1, 6.0, 6.5 };
		TestCase.assertEquals(new BigDecimal("5.349"),
				TimeSeriesExtrapolation.average(TimeSeriesExtrapolation.as(d)).setScale(3, RoundingMode.HALF_UP));
	}

	@Test
	public void testMa() {
		TestCase.assertEquals(
				"[null, null, 5.349, 5.599, 5.875, 6.074, 6.300, 6.350, 6.449, 6.625, 6.724, 6.799, 6.875, 7.000, 7.150, null]",
				Arrays.toString(
						TimeSeriesExtrapolation.aggregateSeasonalFactor(TimeSeriesExtrapolation.as(new Double[] { 4.8,
								4.1, 6.0, 6.5, 5.8, 5.2, 6.8, 7.4, 6.0, 5.6, 7.5, 7.8, 6.3, 5.9, 8.0, 8.4 }), 4)));
	}

	@Test
	public void testCMA() {
		System.out.println(Arrays.toString(
				TimeSeriesExtrapolation.aggregateCMA(TimeSeriesExtrapolation.as(new Double[] { null, null, 5.350, 5.600,
						5.875, 6.075, 6.300, 6.350, 6.450, 6.625, 6.725, 6.800, 6.875, 7.000, 7.150, null }))));
		TestCase.assertEquals(
				"[null, null, 5.474, 5.737, 5.975, 6.187, 6.324, 6.399, 6.537, 6.674, 6.762, 6.837, 6.937, 7.075, null, null]",
				Arrays.toString(TimeSeriesExtrapolation
						.aggregateCMA(TimeSeriesExtrapolation.as(new Double[] { null, null, 5.350, 5.600, 5.875, 6.075,
								6.300, 6.350, 6.450, 6.625, 6.725, 6.800, 6.875, 7.000, 7.150, null }))));
	}

	@Test
	public void testSi() {
		TestCase.assertEquals(
				"[5.149, 4.894, 5.489, 5.691, 6.221, 6.207, 6.221, 6.479, 6.436, 6.684, 6.861, 6.830, 6.758, 7.043, 7.319, 7.355]",
				Arrays.toString(TimeSeriesExtrapolation.si(
						TimeSeriesExtrapolation.as(new Double[] { null, null, 5.474, 5.737, 5.975, 6.187, 6.324, 6.399,
								6.537, 6.674, 6.762, 6.837, 6.937, 7.075, null, null }),
						TimeSeriesExtrapolation.as(new Double[] { 4.8, 4.1, 6.0, 6.5, 5.8, 5.2, 6.8, 7.4, 6.0, 5.6, 7.5,
								7.8, 6.3, 5.9, 8.0, 8.4 }),
						4)));
	}

	@Test
	public void testRegression() {
		SimpleRegression s = TimeSeriesExtrapolation.regression(TimeSeriesExtrapolation.as(
				new Double[] { 1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0, 11.0, 12.0, 13.0, 14.0, 15.0, 16.0 }),
				TimeSeriesExtrapolation.as(new Double[] { 5.149, 4.894, 5.489, 5.691, 6.221, 6.207, 6.221, 6.479, 6.436,
						6.684, 6.861, 6.830, 6.758, 7.043, 7.319, 7.355 }));
		TestCase.assertEquals(5.099925, s.getIntercept());
		TestCase.assertEquals(0.14733970588235296, s.getSlope());
	}

	@Test
	public void testExtrapolation() {
		SimpleRegression s = TimeSeriesExtrapolation.regression(TimeSeriesExtrapolation.as(
				new Double[] { 1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0, 11.0, 12.0, 13.0, 14.0, 15.0, 16.0 }),
				TimeSeriesExtrapolation.as(new Double[] { 5.149, 4.894, 5.489, 5.691, 6.221, 6.207, 6.221, 6.479, 6.436,
						6.684, 6.861, 6.830, 6.758, 7.043, 7.319, 7.355 }));
		BigDecimal[] output = TimeSeriesExtrapolation.extrapolate(s,
				TimeSeriesExtrapolation.as(new Double[] { 1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0, 11.0, 12.0,
						13.0, 14.0, 15.0, 16.0, 17.0, 18.0, 19.0, 20.0, 21.0 }));
		System.out.println(Arrays.toString(output));
	}

	@Test
	public void testFull() {
		BigDecimal[] output = TimeSeriesExtrapolation.extrapolate(TimeSeriesExtrapolation.as(new Double[][] {
				{ 1.0, 4.800 }, { 2.0, 4.100 }, { 3.0, 6.000 }, { 4.0, 6.500 }, { 5.0, 5.800 }, { 6.0, 5.200 },
				{ 7.0, 6.800 }, { 8.0, 7.400 }, { 9.0, 6.000 }, { 10.0, 5.600 }, { 11.0, 7.500 }, { 12.0, 7.800 },
				{ 13.0, 6.300 }, { 14.0, 5.900 }, { 15.0, 8.000 }, { 16.0, 8.400 } }), 4);
		System.out.println(Arrays.toString(output));
	}
}
