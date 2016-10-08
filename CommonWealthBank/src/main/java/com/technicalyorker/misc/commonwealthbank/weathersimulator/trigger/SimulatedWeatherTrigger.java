package com.technicalyorker.misc.commonwealthbank.weathersimulator.trigger;

import java.util.Calendar;

import com.technicalyorker.misc.commonwealthbank.weathersimulator.domain.Position;
import com.technicalyorker.misc.commonwealthbank.weathersimulator.emitter.ConsoleLoggerEmitterAppender;
import com.technicalyorker.misc.commonwealthbank.weathersimulator.emitter.WeatherEmitter;
import com.technicalyorker.misc.commonwealthbank.weathersimulator.service.WeatherService;
import com.technicalyorker.misc.commonwealthbank.weathersimulator.service.WeatherServiceImpl;

public class SimulatedWeatherTrigger extends AbstractWeatherTrigger {
	private final WeatherEmitter emitter = new ConsoleLoggerEmitterAppender();
	private final WeatherService service = new WeatherServiceImpl();

	@Override
	WeatherEmitter getAssociatedEmitter() {
		return emitter;
	}

	@Override
	WeatherService getAssociateService() {
		return service;
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			Position position = new Position("-33.86", "151.21", "39");
			Calendar c = Calendar.getInstance();
			emit(position, c);
		}
	}
}
