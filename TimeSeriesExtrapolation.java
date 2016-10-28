package zzTest;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import org.apache.commons.math3.stat.regression.SimpleRegression;

public class TimeSeriesExtrapolation {
	final static int X = 0;
	final static int Y = 1;
	private static final MathContext m = new MathContext(4, RoundingMode.FLOOR);

	public static void main(String[] args) {

	}

	public static BigDecimal[] extrapolate(final BigDecimal[][] data, final int seasonalFactor) {
		checkDataValid(data, seasonalFactor);
		BigDecimal x[] = extract(data, X);
		BigDecimal y[] = extract(data, Y);
		BigDecimal seasonal[] = aggregateSeasonalFactor(y, seasonalFactor);
		BigDecimal cma[] = aggregateCMA(seasonal);
		BigDecimal si[] = si(cma, y, seasonalFactor);
		SimpleRegression sr = regression(y, si);
		BigDecimal projection[] = extrapolate(sr, y);
		return projection;
	}

	public static void checkDataValid(final BigDecimal[][] data, final int seasonalFactor) {
		for (int i = 0; i < data.length; i++) {
			if (data[i][X] == null || data[i][Y] == null) {
				throw new IllegalStateException("Data is invalid");
			}
		}
		if (seasonalFactor > data.length) {
			throw new IllegalStateException("Seasonal Data Cannot be greater than data points in projection");
		}
	}

	public static BigDecimal[] aggregate(final BigDecimal[] data, final int seasonalFactor) {
		final BigDecimal[] output = new BigDecimal[data.length];
		for (int i = 0; i < data.length; i++) {
			if (i + seasonalFactor > data.length) {
				break;
			}
			output[i + (seasonalFactor) / 2] = averageSkipIfNull(data, i, seasonalFactor);
		}
		return output;
	}

	public static BigDecimal[] aggregateSeasonalFactor(final BigDecimal[] data, final int seasonalFactor) {
		final BigDecimal[] output = new BigDecimal[data.length];
		for (int i = 0; i < data.length; i++) {
			if (i + seasonalFactor > data.length) {
				break;
			}
			output[i + (seasonalFactor) / 2] = averageSkipIfNull(data, i, seasonalFactor);
		}
		return output;
	}

	public static BigDecimal[] aggregateCMA(final BigDecimal[] data) {
		final BigDecimal[] output = new BigDecimal[data.length];
		for (int i = 0; i < data.length; i++) {
			if (i + 1 > data.length) {
				break;
			}
			output[i + (1 / 2)] = averageSkipIfNull(data, i, 2);
		}
		return output;
	}

	public static BigDecimal[] seasonals(final BigDecimal[] cma, final BigDecimal[] y, int seasonalFactor) {
		final BigDecimal[] seasonal = new BigDecimal[seasonalFactor];
		final int[] count = new int[seasonalFactor];
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
		BigDecimal[] output = new BigDecimal[y.length];
		for (int i = 0; i < output.length; i++) {
			output[i] = y[i].divide(seasonal[i % seasonalFactor], m);
		}
		return output;
	}

	public static BigDecimal[] si(final BigDecimal[] cma, final BigDecimal[] y, int seasonalFactor) {
		final BigDecimal[] seasonal = new BigDecimal[seasonalFactor];
		final int[] count = new int[seasonalFactor];
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
		BigDecimal[] output = new BigDecimal[y.length];
		for (int i = 0; i < output.length; i++) {
			output[i] = y[i].divide(seasonal[i % seasonalFactor], m);
		}
		return output;
	}

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

	public static BigDecimal[] extract(BigDecimal[][] a, int axis) {
		BigDecimal[] d = new BigDecimal[a.length];
		for (int i = 0; i < a.length; i++) {
			d[i] = a[i][axis];
		}
		return d;
	}

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

	public static SimpleRegression regression(final BigDecimal[] y, final BigDecimal[] si) {
		final SimpleRegression regression = new SimpleRegression();
		for (int i = 0; i < y.length; i++) {
			regression.addData(y[i].doubleValue(), si[i].doubleValue());
		}
		return regression;
	}

	public static BigDecimal[] extrapolate(SimpleRegression regression, final BigDecimal[] ys) {
		BigDecimal[] output = new BigDecimal[ys.length];
		for (int i = 0; i < output.length; i++) {
			output[i] = ys[i].multiply(new BigDecimal(regression.getSlope()))
					.add(new BigDecimal(regression.getIntercept()), m);
		}
		return output;
	}
}
