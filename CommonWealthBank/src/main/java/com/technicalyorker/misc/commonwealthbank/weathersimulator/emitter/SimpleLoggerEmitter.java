package com.technicalyorker.misc.commonwealthbank.weathersimulator.emitter;

import com.technicalyorker.misc.commonwealthbank.weathersimulator.domain.Weather;

/**
 * This class does the job of displaying the sample Data to the console.
 * 
 * @author achuth
 *
 */
public class SimpleLoggerEmitter implements WeatherDataEmitter {

	public void emit(Weather weather) {
		System.out.println(weather.toString());
	}
}
