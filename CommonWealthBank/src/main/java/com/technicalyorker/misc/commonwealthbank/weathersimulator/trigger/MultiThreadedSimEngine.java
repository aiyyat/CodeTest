package com.technicalyorker.misc.commonwealthbank.weathersimulator.trigger;

import java.util.Calendar;

import com.technicalyorker.misc.commonwealthbank.weathersimulator.domain.Position;
import com.technicalyorker.misc.commonwealthbank.weathersimulator.emitter.Emitter;
import com.technicalyorker.misc.commonwealthbank.weathersimulator.service.WeatherService;

public class MultiThreadedSimEngine extends AbstractWeatherEngine {
	public MultiThreadedSimEngine(WeatherService service, Emitter emitter) {
		super(service, emitter);
	}

	@Override
	public void perform() {
		try {
			Thread t = new Thread(() -> {
				while (true) {
					Position position = new Position("-33.86", "151.21", "39");
					Calendar calendar = Calendar.getInstance();
					emit(position, calendar);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
			t.start();
			System.out.println("Weather Monitor Kicked off!!");
			t.join();
			System.out.println("Emission completed!!");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
