package com.technicalyorker.misc.commonwealthbank.weathersimulator.emitter;

import com.technicalyorker.misc.commonwealthbank.weathersimulator.domain.Weather;

/**
 * Emitter Emits data when requested.
 *
 * Emission is usually into to a Messaging Destination or Emission behaves like
 * a Subject by notifying a list of registered Observers.
 * 
 * @author achuth
 *
 */
public interface WeatherDataEmitter {
	/**
	 * Used to emit data into a destination. e.g downstream system, a Messaging
	 * Queue/Topic or Observers.
	 * 
	 * @param weather
	 */
	public void emit(Weather weather);
}
