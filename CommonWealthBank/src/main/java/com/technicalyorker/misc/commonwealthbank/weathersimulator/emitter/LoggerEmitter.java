package com.technicalyorker.misc.commonwealthbank.weathersimulator.emitter;

import com.technicalyorker.misc.commonwealthbank.weathersimulator.domain.Weather;

public class LoggerEmitter implements Emitter {

	public void emit(Weather weather) {
		System.out.println(weather.toString());
	}
}
