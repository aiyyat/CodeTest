package com.technicalyorker.misc.commonwealthbank.weathersimulator.emitter;

import com.technicalyorker.misc.commonwealthbank.weathersimulator.domain.Weather;
import com.technicalyorker.misc.commonwealthbank.weathersimulator.observers.WeatherDataSubject;

/**
 * This class does the job of displaying the sample Data to the console.
 * 
 * @author achuth
 *
 */
public class DateTimeBasedWeatherDataEmitterSubject extends WeatherDataSubject implements WeatherDataEmitter {
	/**
	 * This is an observer implementation of data emission. Observers are
	 * notified of a change in state when a new sample is received.
	 */
	public void emit(Weather weather) {
		updateChange(weather);
	}
}
