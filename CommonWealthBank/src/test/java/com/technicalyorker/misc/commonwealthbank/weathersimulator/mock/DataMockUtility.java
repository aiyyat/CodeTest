package com.technicalyorker.misc.commonwealthbank.weathersimulator.mock;

import static com.technicalyorker.misc.commonwealthbank.weathersimulator.util.WeatherSimulatorUtil.weatherFromString;

import com.technicalyorker.misc.commonwealthbank.weathersimulator.domain.Weather;

/**
 * Used with Unit Testing
 * 
 * @author achuth
 *
 */
public class DataMockUtility {

	public static Weather aWeatherSample() {
		return weatherFromString("Adelaide,-34.92,138.62,48,2016-10-14T19:30:00Z,CLOUDY,20,1016.8,38");
	}
}
