package com.technicalyorker.misc.commonwealthbank.weathersimulator;

import com.technicalyorker.misc.commonwealthbank.weathersimulator.emitter.SimpleLoggerEmitter;
import com.technicalyorker.misc.commonwealthbank.weathersimulator.engine.SimulatorWeatherEngine;
import com.technicalyorker.misc.commonwealthbank.weathersimulator.engine.WeatherEngine;
import com.technicalyorker.misc.commonwealthbank.weathersimulator.trigger.MultiThreadedTimeBasedTrigger;
import com.technicalyorker.misc.commonwealthbank.weathersimulator.trigger.SimpleTrigger;

/**
 * A Weather Simulator Application needs an instance of a Weather Engine and a
 * trigger. A trigger is any class that implements Trigger and indicates to the
 * Engine when to perform Data Sampling.
 * 
 * @author achuth
 *
 */
public class WeatherSimulatorApplication {
	public static void main(String[] args) {
		WeatherEngine engine = new SimulatorWeatherEngine(new SimpleLoggerEmitter());
		new MultiThreadedTimeBasedTrigger(engine, 10).start();
		new SimpleTrigger(engine).start();
	}
}
