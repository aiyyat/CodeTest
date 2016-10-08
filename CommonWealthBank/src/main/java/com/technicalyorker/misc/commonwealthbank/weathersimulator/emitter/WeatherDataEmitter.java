package com.technicalyorker.misc.commonwealthbank.weathersimulator.emitter;

import com.technicalyorker.misc.commonwealthbank.weathersimulator.domain.Weather;

/**
 * Emission is usually done to a Messaging Queue or by notifying a list of
 * registered Observers in real time.
 * 
 * @author achuth
 *
 */
public interface WeatherDataEmitter {
	public void emit(Weather weather);
}
