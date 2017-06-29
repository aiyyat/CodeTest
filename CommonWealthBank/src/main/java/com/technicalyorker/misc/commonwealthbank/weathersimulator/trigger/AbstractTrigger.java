package com.technicalyorker.misc.commonwealthbank.weathersimulator.trigger;

import com.technicalyorker.misc.commonwealthbank.weathersimulator.engine.WeatherEngine;

/**
 * An Abstract Trigger provides the default implementation of notifyEngine
 * operation that invokes the engine to perform data sampling and emission.
 * 
 * @author achuth
 *
 */
public abstract class AbstractTrigger<T> implements Trigger<T> {
	private WeatherEngine<T> engine;

	AbstractTrigger(WeatherEngine<T> engine) {
		this.engine = engine;
	}

	public final void notifyEngine(T t) {
		engine.sampleAndNotify(t);
	}
}
