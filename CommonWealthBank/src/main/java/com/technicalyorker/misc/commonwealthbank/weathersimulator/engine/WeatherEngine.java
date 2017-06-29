package com.technicalyorker.misc.commonwealthbank.weathersimulator.engine;

import com.technicalyorker.misc.commonwealthbank.weathersimulator.domain.Weather;

/**
 * Weather Engine Typically co-ordinates the Data sampling and Data emission
 * process. A Trigger indicates to the Engine when to perform an Emission.
 * 
 * @author achuth
 *
 */
public interface WeatherEngine<T> {
	/**
	 * Fetches a data sample from the service.
	 * 
	 * @param t
	 * @return
	 */
	public Weather sampleData(T t);

	/**
	 * Notifies the emitters of a new Weather Data Sample.
	 * 
	 * @param weather
	 */
	public void notifyEmitter(Weather weather);

	/**
	 * Default implementation of the life cycle of a weather engine.
	 * 
	 * @param t
	 */
	default void sampleAndNotify(T t) {
		Weather weather = sampleData(t);
		if (weather != null) {
			notifyEmitter(sampleData(t));
		}
	}
}
