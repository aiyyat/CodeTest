package com.technicalyorker.misc.commonwealthbank.weathersimulator.emitter;

import com.technicalyorker.misc.commonwealthbank.weathersimulator.domain.Weather;

public class ConsoleLoggerEmitterAppender implements WeatherEmitter {

	public void emit(Weather weather) {
		System.out.println(weather.toString());
	}
}
