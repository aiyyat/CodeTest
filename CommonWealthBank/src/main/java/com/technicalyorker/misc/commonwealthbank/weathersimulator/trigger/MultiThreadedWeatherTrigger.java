package com.technicalyorker.misc.commonwealthbank.weathersimulator.trigger;

import java.util.Calendar;

import com.technicalyorker.misc.commonwealthbank.weathersimulator.domain.Position;
import com.technicalyorker.misc.commonwealthbank.weathersimulator.emitter.ConsoleLoggerEmitterAppender;
import com.technicalyorker.misc.commonwealthbank.weathersimulator.emitter.WeatherEmitter;
import com.technicalyorker.misc.commonwealthbank.weathersimulator.service.WeatherService;
import com.technicalyorker.misc.commonwealthbank.weathersimulator.service.WeatherServiceImpl;

public class MultiThreadedWeatherTrigger extends AbstractWeatherTrigger {
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
		new Thread(() -> {
			while (true) {
				Position position = new Position("", "", "");
				Calendar calendar = Calendar.getInstance();
				emit(position, calendar);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
		System.out.println("Monitor Kicked off!");
	}
}
