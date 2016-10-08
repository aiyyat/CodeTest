package com.technicalyorker.misc.commonwealthbank.weathersimulator.engine;

import com.technicalyorker.misc.commonwealthbank.weathersimulator.domain.Weather;

/**
 * Weather Engine Typically co-ordinates the Data sampling and Data emission
 * process. A Trigger indicates to the Engine when to perform an Emission.
 * 
 * @author achuth
 *
 */
public interface WeatherEngine {
	public void sampleData();

	public void emit(Weather weather);
}
