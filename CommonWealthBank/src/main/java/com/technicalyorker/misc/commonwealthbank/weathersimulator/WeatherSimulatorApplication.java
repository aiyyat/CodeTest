package com.technicalyorker.misc.commonwealthbank.weathersimulator;

import com.technicalyorker.misc.commonwealthbank.weathersimulator.emitter.LoggerEmitter;
import com.technicalyorker.misc.commonwealthbank.weathersimulator.service.MockWeatherService;
import com.technicalyorker.misc.commonwealthbank.weathersimulator.trigger.MultiThreadedSimEngine;

public class WeatherSimulatorApplication {
	public static void main(String[] args) {
		new MultiThreadedSimEngine(new MockWeatherService(), new LoggerEmitter()).perform();
	}
}
