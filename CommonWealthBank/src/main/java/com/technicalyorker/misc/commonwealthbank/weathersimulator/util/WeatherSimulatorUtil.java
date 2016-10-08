package com.technicalyorker.misc.commonwealthbank.weathersimulator.util;

import static com.technicalyorker.misc.commonwealthbank.weathersimulator.domain.Condition.values;

import java.text.DecimalFormat;
import java.util.Random;

import com.technicalyorker.misc.commonwealthbank.weathersimulator.domain.Condition;

/**
 * This is a helper utility that provides basic input Data to an Engine
 * Simulator.
 * 
 * @author achuth
 *
 */
public class WeatherSimulatorUtil {
	private static final Random random = new Random();
	private static final DecimalFormat df = new DecimalFormat("#.##");

	public static String anyDecimalString() {
		return (random.nextInt() % 2 == 0 ? "-" : "") + df.format(random.nextFloat());
	}

	public static Condition anyCondition() {
		return values()[random.nextInt(999) % 3];
	}

	public static String anyIntegerString() {
		return "" + anyInteger();
	}

	public static Integer anyInteger() {
		return random.nextInt(100);
	}

	public static String anyCountry() {
		StringBuilder sb = new StringBuilder("");
		for (int i = 0; i < 6; i++) {
			sb.append((char) ((i == 0 ? 'A' : 'a') + (random.nextInt(25))));
		}
		return sb.toString();
	}
}
