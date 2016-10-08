package com.technicalyorker.misc.commonwealthbank.weathersimulator.trigger;

import java.util.Calendar;

import com.technicalyorker.misc.commonwealthbank.weathersimulator.domain.Position;
import com.technicalyorker.misc.commonwealthbank.weathersimulator.domain.Weather;
import com.technicalyorker.misc.commonwealthbank.weathersimulator.emitter.WeatherEmitter;
import com.technicalyorker.misc.commonwealthbank.weathersimulator.service.WeatherService;

public abstract class AbstractWeatherTrigger implements WeatherTrigger {

	public abstract void run();

	public void perform() {
		run();
	}

	public void emit(Position position, Calendar time) {
		Weather weather = getAssociateService().read(position, time);
		getAssociatedEmitter().emit(weather);
	}

	abstract WeatherEmitter getAssociatedEmitter();

	abstract WeatherService getAssociateService();
}
