package com.technicalyorker.misc.commonwealthbank.weathersimulator.engine;

import com.technicalyorker.misc.commonwealthbank.weathersimulator.domain.Weather;
import com.technicalyorker.misc.commonwealthbank.weathersimulator.emitter.WeatherDataEmitter;

/**
 * Abstract Weather Engine provides default implementation of emit method. A
 * Sample Data could be overridden by children to indicate the methodolgy of
 * Data Sampling. This could be from Database or even from a weather webservice
 * provider such as Yahoo!.
 * 
 * @author achuth
 *
 */
public abstract class AbstractWeatherEngine<T> implements WeatherEngine<T> {
	private WeatherDataEmitter emitter;

	public AbstractWeatherEngine(WeatherDataEmitter emitter) {
		this.emitter = emitter;
	}

	public void notifyEmitter(Weather weather) {
		emitter.emit(weather);
	}
}
