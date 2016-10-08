package com.technicalyorker.misc.commonwealthbank.weathersimulator.service;

import static com.technicalyorker.misc.commonwealthbank.weathersimulator.util.WeatherSimulatorUtil.anyCondition;
import static com.technicalyorker.misc.commonwealthbank.weathersimulator.util.WeatherSimulatorUtil.anyCountry;
import static com.technicalyorker.misc.commonwealthbank.weathersimulator.util.WeatherSimulatorUtil.anyDecimalString;
import static com.technicalyorker.misc.commonwealthbank.weathersimulator.util.WeatherSimulatorUtil.anyIntegerString;
import static java.util.Calendar.getInstance;

import com.technicalyorker.misc.commonwealthbank.weathersimulator.domain.Position;
import com.technicalyorker.misc.commonwealthbank.weathersimulator.domain.Weather;

/**
 * This Service typically constructs MockData using a Mocking Utility
 * 
 * @author achuth
 *
 */
public class MockWeatherService implements WeatherService {

	@Override
	public Weather readNext() {
		Position position = new Position(anyDecimalString(), anyDecimalString(), anyIntegerString());
		return Weather.builder().location(anyCountry()).humidity(anyIntegerString()).localtime(getInstance())
				.position(position).condition(anyCondition()).pressure(anyDecimalString())
				.temperature(anyDecimalString()).temperature(anyDecimalString()).build();
	}
}
