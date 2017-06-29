package com.technicalyorker.misc.commonwealthbank.weathersimulator.constants;

import java.math.BigDecimal;

/**
 * Constants used by the Weather simulator Application.
 * 
 * @author achuth
 *
 */
public class WeatherSimulatorConstants {
	public static final Object SEPERATOR = "|";
	public static final Integer LOCATION = 0;
	public static final Integer LATITUDE = 1;
	public static final Integer LONGITUDE = 2;
	public static final Integer ALTITUDE = 3;
	public static final Integer LOCALTIME = 4;
	public static final Integer CONDITION = 5;
	public static final Integer TEMPERATURE = 6;
	public static final Integer PRESSURE = 7;
	public static final Integer HUMIDITY = 8;
	public static final BigDecimal UPPERTHRESHOLD = new BigDecimal(20);
	public static final BigDecimal LOWERTHRESHOLD = new BigDecimal(10);
	public static final char SUN = '\u263C';
	public static final char FLAKE = '\u2744';
	public static final char DEGREE = '\u00B0';
	public static final char SKULL = '\u2620';
	public static final int INTERVAL_IN_MINUTES = 30;
	public static final int NO_OF_DATA_PTS = 98;
	public static final int X = 0;
	public static final int Y = 1;
	public static final String PROJECTION_TABLE_HEADER = "\nTimeStamp\t\tOri.Temp(" + DEGREE + "c)\t\tCalc.Temp("
			+ DEGREE + "c)\tOri.Rel.Humidity(%)\tCalc.Rel.Humidity(%)";
	public static final String PROJECTION_OPENING_MSG = "\nTime Series Temperature & Relative Humidity Forecast):\n";
}
