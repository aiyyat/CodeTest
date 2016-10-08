package com.technicalyorker.misc.commonwealthbank.weathersimulator;

import com.technicalyorker.misc.commonwealthbank.weathersimulator.emitter.LoggerEmitter;
import com.technicalyorker.misc.commonwealthbank.weathersimulator.service.MockWeatherService;
import com.technicalyorker.misc.commonwealthbank.weathersimulator.trigger.MultiThreadedTimeBasedSimulatorEngine;

public class WeatherSimulatorApplication {
	public static void main(String[] args) {
		new MultiThreadedTimeBasedSimulatorEngine(new MockWeatherService(), new LoggerEmitter(), 10).perform();
	}
}
