package com.technicalyorker.misc.commonwealthbank.weathersimulator.service;

import static com.technicalyorker.misc.commonwealthbank.weathersimulator.util.WeatherSimulatorUtil.format;
import static com.technicalyorker.misc.commonwealthbank.weathersimulator.util.WeatherSimulatorUtil.weatherFromString;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import com.technicalyorker.misc.commonwealthbank.weathersimulator.domain.Weather;
import com.technicalyorker.misc.commonwealthbank.weathersimulator.exception.WeatherDataSampleException;

/**
 * This Service typically constructs data from a properties file data.properties
 * and exposes it as a Map simulating a Source of Data such as RDMS
 * 
 * @author achuth
 *
 */
public class DateTimeBasedWeatherService implements WeatherService<LocalDateTime> {
	private final Map<String, Weather> data = new HashMap<>();

	public DateTimeBasedWeatherService() {
		try (final BufferedReader fos = new BufferedReader(
				new FileReader(getClass().getClassLoader().getResource("data.properties").getFile()))) {
			String line = null;
			while ((line = fos.readLine()) != null) {
				Weather w = weatherFromString(line);
				data.put(w.getLocalTimeAsString(), w);
			}
		} catch (NumberFormatException | IOException e) {
			throw new WeatherDataSampleException(e);
		}
	}

	@Override
	public Weather readNextSample(LocalDateTime t) {
		return data.get(format(t));
	}
}
