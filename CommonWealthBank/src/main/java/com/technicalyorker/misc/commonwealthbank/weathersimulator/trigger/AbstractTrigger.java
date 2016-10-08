package com.technicalyorker.misc.commonwealthbank.weathersimulator.trigger;

import com.technicalyorker.misc.commonwealthbank.weathersimulator.engine.WeatherEngine;

/**
 * An Abstract Trigger provides the default implementation of notifyEngine
 * operation that invokes the engine to perform data sampling and emission.
 * 
 * @author achuth
 *
 */
public abstract class AbstractTrigger implements Trigger {
	private WeatherEngine engine;

	AbstractTrigger(WeatherEngine engine) {
		this.engine = engine;
	}

	public void notifyEngine() {
		engine.sampleData();
	}
}
