package com.technicalyorker.misc.commonwealthbank.weathersimulator.trigger;

import java.time.LocalTime;
import java.util.Calendar;

import com.technicalyorker.misc.commonwealthbank.weathersimulator.domain.Position;
import com.technicalyorker.misc.commonwealthbank.weathersimulator.emitter.Emitter;
import com.technicalyorker.misc.commonwealthbank.weathersimulator.service.WeatherService;

public class MultiThreadedTimeBasedSimulatorEngine extends AbstractWeatherEngine {
	private Integer timeInSeconds;

	public MultiThreadedTimeBasedSimulatorEngine(WeatherService service, Emitter emitter, Integer timeInSeconds) {
		super(service, emitter);
		this.timeInSeconds = timeInSeconds;
	}

	@Override
	public void perform() {
		LocalTime endtime = LocalTime.now().plusSeconds(timeInSeconds);
		try {
			Thread t = new Thread(() -> {
				while (endtime.isAfter(LocalTime.now())) {
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
