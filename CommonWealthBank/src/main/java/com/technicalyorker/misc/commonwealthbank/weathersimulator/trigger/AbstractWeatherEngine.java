package com.technicalyorker.misc.commonwealthbank.weathersimulator.trigger;

import java.util.Calendar;

import com.technicalyorker.misc.commonwealthbank.weathersimulator.domain.Position;
import com.technicalyorker.misc.commonwealthbank.weathersimulator.domain.Weather;
import com.technicalyorker.misc.commonwealthbank.weathersimulator.emitter.Emitter;
import com.technicalyorker.misc.commonwealthbank.weathersimulator.service.WeatherService;

public abstract class AbstractWeatherEngine implements WeatherEngine {
	private WeatherService service;
	private Emitter emitter;

	public AbstractWeatherEngine(WeatherService service, Emitter emitter) {
		this.service = service;
		this.emitter = emitter;
	}

	public void emit(Position position, Calendar time) {
		Weather weather = service.read(position, time);
		emitter.emit(weather);
	}
}
