package com.technicalyorker.misc.commonwealthbank.weathersimulator.trigger;

import com.technicalyorker.misc.commonwealthbank.weathersimulator.engine.WeatherEngine;

/**
 * A Simple Trigger that just runs a loop of 10 to indicate to the Engine to
 * push data to the Emitter.
 * 
 * @author achuth
 *
 */
public class SimpleTrigger extends AbstractTrigger {

	public SimpleTrigger(WeatherEngine engine) {
		super(engine);
	}

	@Override
	public void start() {
		System.out.println("Weather Monitor Kicked off!!");
		for (int i = 0; i < 10; i++) {
			notifyEngine();
		}
		System.out.println("Emission completed!!");
	}
}
