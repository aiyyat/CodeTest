package com.technicalyorker.misc.commonwealthbank.weathersimulator.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

import org.apache.commons.math3.stat.regression.SimpleRegression;
import org.junit.Test;

import com.technicalyorker.misc.commonwealthbank.weathersimulator.exception.TimeSeriesInputDataException;
import com.technicalyorker.misc.commonwealthbank.weathersimulator.util.TimeSeriesExtrapolation;

import junit.framework.TestCase;

public class TimeSeriesExtrapolationTest {
	@Test(expected = TimeSeriesInputDataException.class)
	public void testValidation() {
		TimeSeriesExtrapolation.checkDataValid(TimeSeriesExtrapolation.as(new Double[][] { { 1.00, 12.20 },
				{ 2.00, 11.80 }, { 3.00, 11.40 }, { 4.00, 10.70 }, { 5.00, 11.00 }, { 6.00, 11.00 } }), 7, 3);
	}

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
				Arrays.toString(TimeSeriesExtrapolation.movingAverage(TimeSeriesExtrapolation.as(new Double[] { 4.8,
						4.1, 6.0, 6.5, 5.8, 5.2, 6.8, 7.4, 6.0, 5.6, 7.5, 7.8, 6.3, 5.9, 8.0, 8.4 }), 4)));
	}

	@Test
	public void testCMA() {
		TestCase.assertEquals(
				"[null, null, 5.474, 5.737, 5.975, 6.187, 6.324, 6.399, 6.537, 6.674, 6.762, 6.837, 6.937, 7.075, null, null]",
				Arrays.toString(TimeSeriesExtrapolation
						.centerMovingAverage(TimeSeriesExtrapolation.as(new Double[] { null, null, 5.350, 5.600, 5.875,
								6.075, 6.300, 6.350, 6.450, 6.625, 6.725, 6.800, 6.875, 7.000, 7.150, null }))));
	}

	@Test
	public void testSi() {
		BigDecimal[] seasons = TimeSeriesExtrapolation.smoothing(
				TimeSeriesExtrapolation.as(new Double[] { null, null, 5.474, 5.737, 5.975, 6.187, 6.324, 6.399, 6.537,
						6.674, 6.762, 6.837, 6.937, 7.075, null, null }),
				TimeSeriesExtrapolation.as(new Double[] { 4.8, 4.1, 6.0, 6.5, 5.8, 5.2, 6.8, 7.4, 6.0, 5.6, 7.5, 7.8,
						6.3, 5.9, 8.0, 8.4 }),
				4);
		TestCase.assertEquals(
				"[5.149, 4.894, 5.489, 5.691, 6.221, 6.207, 6.221, 6.479, 6.436, 6.684, 6.861, 6.830, 6.758, 7.043, 7.319, 7.355]",
				Arrays.toString(TimeSeriesExtrapolation.deseasonlizable(TimeSeriesExtrapolation.as(new Double[] { 4.8,
						4.1, 6.0, 6.5, 5.8, 5.2, 6.8, 7.4, 6.0, 5.6, 7.5, 7.8, 6.3, 5.9, 8.0, 8.4 }), seasons)));
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
		BigDecimal[] seasons = TimeSeriesExtrapolation.smoothing(
				TimeSeriesExtrapolation.centerMovingAverage(TimeSeriesExtrapolation.as(new Double[] { null, null, 5.350,
						5.600, 5.875, 6.075, 6.300, 6.350, 6.450, 6.625, 6.725, 6.800, 6.875, 7.000, 7.150, null })),
				TimeSeriesExtrapolation.as(new Double[] { 4.8, 4.1, 6.0, 6.5, 5.8, 5.2, 6.8, 7.4, 6.0, 5.6, 7.5, 7.8,
						6.3, 5.9, 8.0, 8.4 }),
				4);
		SimpleRegression s = TimeSeriesExtrapolation.regression(TimeSeriesExtrapolation.as(
				new Double[] { 1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0, 11.0, 12.0, 13.0, 14.0, 15.0, 16.0 }),
				TimeSeriesExtrapolation.as(new Double[] { 5.149, 4.894, 5.489, 5.691, 6.221, 6.207, 6.221, 6.479, 6.436,
						6.684, 6.861, 6.830, 6.758, 7.043, 7.319, 7.355 }));
		BigDecimal[] output = TimeSeriesExtrapolation.performProjection(s,
				TimeSeriesExtrapolation.as(new Double[] { 1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0, 11.0, 12.0,
						13.0, 14.0, 15.0, 16.0, 17.0, 18.0, 19.0, 20.0, 21.0 }),
				seasons);
		TestCase.assertEquals(
				"[4.891, 4.519, 6.057, 6.497, 5.440, 5.012, 6.701, 7.170, 5.990, 5.506, 7.345, 7.843, 6.539, 6.000, 7.989, 8.516, 7.089, 6.493, 8.634, 9.189, 7.638]",
				Arrays.toString(output));
	}

	@Test
	public void testFull() {
		BigDecimal[] output = TimeSeriesExtrapolation.extrapolate(TimeSeriesExtrapolation.as(new Double[][] {
				{ 1.0, 4.800 }, { 2.0, 4.100 }, { 3.0, 6.000 }, { 4.0, 6.500 }, { 5.0, 5.800 }, { 6.0, 5.200 },
				{ 7.0, 6.800 }, { 8.0, 7.400 }, { 9.0, 6.000 }, { 10.0, 5.600 }, { 11.0, 7.500 }, { 12.0, 7.800 },
				{ 13.0, 6.300 }, { 14.0, 5.900 }, { 15.0, 8.000 }, { 16.0, 8.400 }, { 17.0, -1.0 }, { 18.0, -1.0 },
				{ 19.0, -1.0 }, { 20.0, -1.0 }, { 21.0, -1.0 } }), 4, 16);
		TestCase.assertEquals(
				"[4.891, 4.519, 6.057, 6.497, 5.440, 5.012, 6.701, 7.170, 5.990, 5.506, 7.345, 7.843, 6.539, 6.000, 7.989, 8.516, 7.089, 6.493, 8.634, 9.189, 7.638]",
				Arrays.toString(output));
	}

	@Test
	public void testReal() {
		BigDecimal[] output = TimeSeriesExtrapolation.extrapolate(TimeSeriesExtrapolation.as(new Double[][] {
				{ 1.00, 12.20 }, { 2.00, 11.80 }, { 3.00, 11.40 }, { 4.00, 10.70 }, { 5.00, 11.00 }, { 6.00, 11.00 },
				{ 7.00, 10.60 }, { 8.00, 10.60 }, { 9.00, 10.80 }, { 10.00, 10.60 }, { 11.00, 10.50 }, { 12.00, 10.10 },
				{ 13.00, 9.80 }, { 14.00, 9.80 }, { 15.00, 10.90 }, { 16.00, 12.20 }, { 17.00, 13.80 },
				{ 18.00, 14.40 }, { 19.00, 15.00 }, { 20.00, 15.30 }, { 21.00, 16.20 }, { 22.00, 16.10 },
				{ 23.00, 16.70 }, { 24.00, 17.70 }, { 25.00, 16.90 }, { 26.00, 17.00 }, { 27.00, 18.90 },
				{ 28.00, 19.10 }, { 29.00, 19.80 }, { 30.00, 19.30 }, { 31.00, 20.20 }, { 32.00, 20.00 },
				{ 33.00, 20.20 }, { 34.00, 20.30 }, { 35.00, 20.40 }, { 36.00, 20.00 }, { 37.00, 19.50 },
				{ 38.00, 18.90 }, { 39.00, 17.90 }, { 40.00, 16.70 }, { 41.00, 15.60 }, { 42.00, 14.60 },
				{ 43.00, 14.20 }, { 44.00, 13.70 }, { 45.00, 13.30 }, { 46.00, 13.20 }, { 47.00, 12.90 },
				{ 48.00, 12.50 }, { 49.00, 11.90 }, { 50.00, 11.30 }, { 51.00, 11.50 }, { 52.00, 10.50 },
				{ 53.00, 10.30 }, { 54.00, 10.20 }, { 55.00, 9.80 }, { 56.00, 9.40 }, { 57.00, 9.50 }, { 58.00, 9.30 },
				{ 59.00, 8.80 }, { 60.00, 9.00 }, { 61.00, 9.00 }, { 62.00, 8.70 }, { 63.00, 9.30 }, { 64.00, 11.00 },
				{ 65.00, 12.60 }, { 66.00, 14.70 }, { 67.00, 16.10 }, { 68.00, 17.10 }, { 69.00, 18.00 },
				{ 70.00, 18.40 }, { 71.00, 19.40 }, { 72.00, 19.60 }, { 73.00, 20.30 }, { 74.00, 20.80 },
				{ 75.00, 20.70 }, { 76.00, 20.90 }, { 77.00, 21.50 }, { 78.00, 22.00 }, { 79.00, 21.60 },
				{ 80.00, 21.50 }, { 81.00, 21.90 }, { 82.00, 21.20 }, { 83.00, 21.30 }, { 84.00, 20.80 },
				{ 85.00, 20.40 }, { 86.00, 19.70 }, { 87.00, 19.10 }, { 88.00, 18.80 }, { 89.00, 18.00 },
				{ 90.00, 17.20 }, { 91.00, 16.10 }, { 92.00, 15.40 }, { 93.00, 15.00 }, { 94.00, 14.10 },
				{ 95.00, 13.80 }, { 96.00, 13.10 }, { 97.00, -1.00 }, { 98.00, -1.00 }, { 99.00, -1.00 },
				{ 100.00, -1.00 }, { 101.00, -1.00 }, { 102.00, -1.00 }, { 103.00, -1.00 } }), 48, 96);
		TestCase.assertEquals(
				"[12.28, 11.60, 11.77, 10.72, 10.49, 10.36, 9.936, 9.514, 9.593, 9.375, 8.862, 9.059, 9.049, 8.739, 9.333, 11.02, 12.58, 14.64, 15.98, 16.94, 17.79, 18.16, 19.12, 19.31, 17.50, 17.63, 19.59, 19.81, 20.56, 20.07, 21.03, 20.87, 21.10, 21.26, 21.41, 21.04, 20.56, 19.94, 18.94, 17.71, 16.57, 15.52, 15.09, 14.53, 14.08, 13.94, 13.57, 13.11, 12.44, 11.75, 11.92, 10.86, 10.63, 10.50, 10.06, 9.640, 9.721, 9.499, 8.979, 9.179, 9.169, 8.854, 9.456, 11.16, 12.75, 14.83, 16.19, 17.16, 18.02, 18.40, 19.37, 19.57, 17.73, 17.86, 19.85, 20.07, 20.83, 20.33, 21.31, 21.14, 21.38, 21.54, 21.69, 21.32, 20.83, 20.21, 19.19, 17.94, 16.79, 15.73, 15.28, 14.72, 14.26, 14.12, 13.75, 13.28, 12.60, 11.91, 12.08, 11.00, 10.77, 10.64, 10.20]",
				Arrays.toString(output));
	}
}
