package com.technicalyorker.misc.commonwealthbank.weathersimulator.util;

import static com.technicalyorker.misc.commonwealthbank.weathersimulator.constants.WeatherSimulatorConstants.ALTITUDE;
import static com.technicalyorker.misc.commonwealthbank.weathersimulator.constants.WeatherSimulatorConstants.CONDITION;
import static com.technicalyorker.misc.commonwealthbank.weathersimulator.constants.WeatherSimulatorConstants.HUMIDITY;
import static com.technicalyorker.misc.commonwealthbank.weathersimulator.constants.WeatherSimulatorConstants.LATITUDE;
import static com.technicalyorker.misc.commonwealthbank.weathersimulator.constants.WeatherSimulatorConstants.LOCATION;
import static com.technicalyorker.misc.commonwealthbank.weathersimulator.constants.WeatherSimulatorConstants.LONGITUDE;
import static com.technicalyorker.misc.commonwealthbank.weathersimulator.constants.WeatherSimulatorConstants.PRESSURE;
import static com.technicalyorker.misc.commonwealthbank.weathersimulator.constants.WeatherSimulatorConstants.TEMPERATURE;
import static java.sql.Timestamp.valueOf;
import static java.time.Instant.parse;
import static java.time.LocalDateTime.ofInstant;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import com.technicalyorker.misc.commonwealthbank.weathersimulator.constants.WeatherSimulatorConstants;
import com.technicalyorker.misc.commonwealthbank.weathersimulator.domain.Condition;
import com.technicalyorker.misc.commonwealthbank.weathersimulator.domain.Position;
import com.technicalyorker.misc.commonwealthbank.weathersimulator.domain.Weather;
import com.technicalyorker.misc.commonwealthbank.weathersimulator.exception.WeatherDataSampleException;

/**
 * This is a helper utility that provides basic input Data to an Engine
 * Simulator.
 * 
 * @author achuth
 *
 */
public class WeatherSimulatorUtil {
	private static final ZoneOffset UTC = ZoneOffset.UTC;
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

	static {
	}

	public static String format(LocalDateTime localTime) {
		if (null == localTime) {
			throw new WeatherDataSampleException("LocalTime cannot be set to null");
		}
		return sdf.format(valueOf(localTime));
	}

	public static Weather weatherFromString(String line) {
		String[] s = line.split(",");
		Position position = new Position(new BigDecimal(s[LATITUDE]), new BigDecimal(s[LONGITUDE]),
				new Integer(s[ALTITUDE]));
		Weather w = Weather.builder().location(s[LOCATION]).humidity(new Integer(s[HUMIDITY]))
				.localtime(format(s[WeatherSimulatorConstants.LOCALTIME])).position(position)
				.condition(Condition.valueOf(s[CONDITION])).pressure(new BigDecimal(s[PRESSURE]))
				.temperature(new BigDecimal(s[TEMPERATURE])).build();
		return w;
	}

	public static LocalDateTime format(String localTime) {
		Instant instant = parse(localTime);
		return ofInstant(instant, UTC);
	}

	public static String hiphenForNull(BigDecimal bigDecimal) {
		return bigDecimal == null ? "--" : bigDecimal.toPlainString();
	}

	public static String emptyIfNull(BigInteger bigDecimal) {
		return bigDecimal == null ? "--" : bigDecimal.toString();
	}
}
