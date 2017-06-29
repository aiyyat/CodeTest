package com.technicalyorker.misc.commonwealthbank.weathersimulator.util;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import org.apache.commons.math3.stat.regression.SimpleRegression;

import com.technicalyorker.misc.commonwealthbank.weathersimulator.exception.TimeSeriesInputDataException;

/**
 * Utility for TimeSeries Extrapolation.
 * 
 * Reference: https://www.youtube.com/watch?v=gHdYEZA50KE by Jalayer Academy.
 * 
 * @author achuth
 *
 */
public class TimeSeriesExtrapolation {
	private static int X = 0;
	private static int Y = 1;
	private static MathContext m = new MathContext(4, RoundingMode.FLOOR);

	public static BigDecimal[] extrapolate(BigDecimal[][] data, int seasonalFactor, int datarange) {
		checkDataValid(data, seasonalFactor, datarange);
		BigDecimal x[] = extract(data, X, datarange);
		BigDecimal y[] = extract(data, Y, datarange);
		BigDecimal seasonal[] = movingAverage(y, seasonalFactor);
		BigDecimal cma[] = centerMovingAverage(seasonal);
		BigDecimal seasons[] = smoothing(cma, y, seasonalFactor);
		BigDecimal si[] = deseasonlizable(y, seasons);
		SimpleRegression sr = regression(x, si);
		BigDecimal[] output = performProjection(sr, extract(data, X, data.length), seasons);
		return output;
	}

	/**
	 * Simple Validation Check on the data
	 * 
	 * @param data
	 * @param seasonalFactor
	 * @param datarange
	 */
	public static void checkDataValid(BigDecimal[][] data, int seasonalFactor, int datarange) {
		for (int i = 0; i < data.length; i++) {
			if (data[i][X] == null || (i < datarange && data[i][Y] == null)) {
				throw new TimeSeriesInputDataException(
						data[i][X] + "/" + i + "&" + datarange + "/" + data[i][Y] + "Data is invalid");
			}
		}
		if (seasonalFactor > data.length) {
			throw new TimeSeriesInputDataException("Seasonal Data Cannot be greater than data points in projection");
		}
	}

	/**
	 * This method is to find the moving average (MA).
	 * 
	 * Smoothing the line based on the seasonalFactor.This is done by finding
	 * the simple average of seasonalFactor number of data points. i.e average
	 * starting 0 to seasonalFactor (will be the value corresponding to center
	 * of 0 to seasonalFactor), 1 to seasonalFactor+1 etc.
	 * 
	 * @param data
	 * @param seasonalFactor
	 * @return
	 */
	public static BigDecimal[] movingAverage(BigDecimal[] data, int seasonalFactor) {
		BigDecimal[] output = new BigDecimal[data.length];
		for (int i = 0; i < data.length; i++) {
			if (i + seasonalFactor > data.length) {
				break;
			}
			output[i + (seasonalFactor) / 2] = averageSkipIfNull(data, i, seasonalFactor);
		}
		return output;
	}

	/**
	 * Center moving average (CMA).
	 * 
	 * @param data
	 * @return
	 */
	public static BigDecimal[] centerMovingAverage(BigDecimal[] data) {
		BigDecimal[] output = new BigDecimal[data.length];
		for (int i = 0; i < data.length; i++) {
			if (i + 1 > data.length) {
				break;
			}
			output[i + (1 / 2)] = averageSkipIfNull(data, i, 2);
		}
		return output;
	}

	/**
	 * Smoothing essentially means taking out the seasonal component and
	 * irregular component.
	 * 
	 * This step is to extract the seasonality and irregularity, in other words
	 * find its deviation from the original values. Original values (Yt) is
	 * considered a product of Seasonal Component (St), Irregular component (It)
	 * and Trend (t). Here we divide the original values from the center moving
	 * average (Yt/CMA).Then finally getting rid of the irrgularity component by
	 * using the corresponding values for each seasonal index and finding the
	 * average of all values.
	 * 
	 * @param cma
	 * @param y
	 * @param seasonalFactor
	 * @return
	 */
	public static BigDecimal[] smoothing(BigDecimal[] cma, BigDecimal[] y, int seasonalFactor) {
		BigDecimal[] seasonal = new BigDecimal[seasonalFactor];
		int[] count = new int[seasonalFactor];
		for (int i = 0; i < seasonalFactor; i++) {
			seasonal[i] = new BigDecimal(0);
		}
		for (int i = 0; i < cma.length; i++) {
			if (null == cma[i]) {
				continue;
			}
			seasonal[i % seasonalFactor] = seasonal[i % seasonalFactor].add(y[i].divide(cma[i], m));
			count[i % seasonalFactor]++;
		}
		for (int i = 0; i < seasonalFactor; i++) {
			seasonal[i] = seasonal[i].divide(new BigDecimal(count[i]), m);
		}
		return seasonal;
	}

	/**
	 * After Finding the Seasonal Component (St), the original values(Yt) are
	 * divided by the Seasonal component (St) to do deseasonlizable. Now we have
	 * get rid of the SeasonalComponent and Irregular Component.
	 * 
	 * @param y
	 * @param seasonal
	 * @return
	 */
	public static BigDecimal[] deseasonlizable(BigDecimal[] y, BigDecimal[] seasonal) {
		int seasonalFactor = seasonal.length;
		BigDecimal[] output = new BigDecimal[y.length];
		for (int i = 0; i < output.length; i++) {
			output[i] = y[i].divide(seasonal[i % seasonalFactor], m);
		}
		return output;
	}

	/**
	 * Finding the simple Linear Regression.
	 * 
	 * Apache commons Math is used here to find the Simple Linear Regression. In
	 * other words finding the coefficients for y-intercept and slope. This is
	 * used to find the trend Component (Tt).
	 * 
	 * @param x
	 * @param si
	 * @return
	 */
	public static SimpleRegression regression(BigDecimal[] x, BigDecimal[] si) {
		SimpleRegression regression = new SimpleRegression();
		for (int i = 0; i < x.length; i++) {
			regression.addData(x[i].doubleValue(), si[i].doubleValue());
		}
		return regression;
	}

	public static BigDecimal[] performProjection(SimpleRegression regression, BigDecimal[] x, BigDecimal[] st) {
		BigDecimal[] output = new BigDecimal[x.length];
		for (int i = 0; i < output.length; i++) {
			output[i] = st[i % st.length].multiply(
					x[i].multiply(new BigDecimal(regression.getSlope())).add(new BigDecimal(regression.getIntercept())),
					m);
		}
		return output;
	}

	/**
	 * Method to find average skipping the null values.
	 * 
	 * @param data
	 * @param seasonalFactor
	 * @return
	 */
	public static BigDecimal[] aggregate(BigDecimal[] data, int seasonalFactor) {
		BigDecimal[] output = new BigDecimal[data.length];
		for (int i = 0; i < data.length; i++) {
			if (i + seasonalFactor > data.length) {
				break;
			}
			output[i + (seasonalFactor) / 2] = averageSkipIfNull(data, i, seasonalFactor);
		}
		return output;
	}

	/**
	 * Extract out relevant data from 2D array based on the datarange and axis
	 * (x or y)
	 * 
	 * @param a
	 * @param axis
	 * @param datarange
	 * @return
	 */
	public static BigDecimal[] extract(BigDecimal[][] a, int axis, int datarange) {
		BigDecimal[] d = new BigDecimal[datarange];
		for (int i = 0; i < datarange; i++) {
			d[i] = a[i][axis];
		}
		return d;
	}

	/**
	 * Get 2D - Double values as BigDecimal Values
	 * 
	 * @param a
	 * @return
	 */
	public static BigDecimal[][] as(Double[][] a) {
		BigDecimal[][] d = new BigDecimal[a.length][];
		for (int i = 0; i < a.length; i++) {
			d[i] = new BigDecimal[a[i].length];
			for (int j = 0; j < a[i].length; j++) {
				d[i][j] = new BigDecimal(a[i][j]);
			}
		}
		return d;
	}

	/**
	 * 1D - Double to BigDecimal
	 * 
	 * @param a
	 * @return
	 */
	public static BigDecimal[] as(Double[] a) {
		BigDecimal[] d = new BigDecimal[a.length];
		for (int j = 0; j < a.length; j++) {
			if (a[j] != null) {
				d[j] = new BigDecimal(a[j]);
			} else {
				d[j] = null;
			}
		}
		return d;
	}

	public static BigDecimal average(BigDecimal[] bigDecimals) {
		return averageSkipIfNull(bigDecimals, 0, bigDecimals.length);
	}

	public static BigDecimal averageSkipIfNull(BigDecimal[] bigDecimals, int start, int numberOfSamples) {
		BigDecimal sum = new BigDecimal("0");
		int i = 0;
		for (i = start; i < start + numberOfSamples; i++) {
			if (bigDecimals[i] == null) {
				return null;
			}
			sum = sum.add(bigDecimals[i]);
		}
		return sum.divide(new BigDecimal(i - start), m);
	}
}
