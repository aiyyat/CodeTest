package com.technicalyorker.misc.commonwealthbank.weathersimulator.service;

import java.util.Calendar;

import com.technicalyorker.misc.commonwealthbank.weathersimulator.domain.Position;
import com.technicalyorker.misc.commonwealthbank.weathersimulator.domain.Weather;

/**
 * @author achuth
 *
 */
public class WeatherServiceImpl implements WeatherService {
	public Weather read(Position location, Calendar time) {
		return Weather.builder().position(location).localtime(time).build();
	}
}
