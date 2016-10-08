package com.technicalyorker.misc.commonwealthbank.weathersimulator.service;

import static com.technicalyorker.misc.commonwealthbank.weathersimulator.util.WeatherSimulatorUtil.anyDecimalString;
import static com.technicalyorker.misc.commonwealthbank.weathersimulator.util.WeatherSimulatorUtil.anyIntegerString;

import java.util.Calendar;

import com.technicalyorker.misc.commonwealthbank.weathersimulator.domain.Position;
import com.technicalyorker.misc.commonwealthbank.weathersimulator.domain.Weather;
import com.technicalyorker.misc.commonwealthbank.weathersimulator.util.WeatherSimulatorUtil;

/**
 * @author achuth
 *
 */
public class MockWeatherService implements WeatherService {

	@Override
	public Weather readNext() {
		Position position = new Position(anyDecimalString(), anyDecimalString(), anyIntegerString());
		return Weather.builder().location(WeatherSimulatorUtil.anyCountry()).humidity(anyIntegerString())
				.localtime(Calendar.getInstance()).position(position).condition(WeatherSimulatorUtil.anyCondition())
				.pressure(WeatherSimulatorUtil.anyDecimalString()).temperature(WeatherSimulatorUtil.anyDecimalString())
				.temperature(WeatherSimulatorUtil.anyDecimalString()).build();
	}
}
