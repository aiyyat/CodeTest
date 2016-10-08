package com.technicalyorker.misc.commonwealthbank.weathersimulator.trigger;

import java.util.Calendar;

import com.technicalyorker.misc.commonwealthbank.weathersimulator.domain.Position;
import com.technicalyorker.misc.commonwealthbank.weathersimulator.emitter.Emitter;
import com.technicalyorker.misc.commonwealthbank.weathersimulator.service.WeatherService;

public class SimpleSimEngine extends AbstractWeatherEngine {

	public SimpleSimEngine(WeatherService service, Emitter emitter) {
		super(service, emitter);
	}

	@Override
	public void perform() {
		System.out.println("Weather Monitor Kicked off!!");
		for (int i = 0; i < 10; i++) {
			Position position = new Position("-33.86", "151.21", "39");
			Calendar c = Calendar.getInstance();
			emit(position, c);
		}
		System.out.println("Emission completed!!");
	}
}
