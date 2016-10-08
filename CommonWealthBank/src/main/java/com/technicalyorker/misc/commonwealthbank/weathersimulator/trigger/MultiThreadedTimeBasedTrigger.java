package com.technicalyorker.misc.commonwealthbank.weathersimulator.trigger;

import java.time.LocalTime;

import com.technicalyorker.misc.commonwealthbank.weathersimulator.engine.WeatherEngine;

/**
 * This class starts a thread that indicates to the Engine in a defined
 * timeInterval to perform sampling. The timeInSeconds indicate how long the
 * trigger must run.
 * 
 * @author achuth
 *
 */
public class MultiThreadedTimeBasedTrigger extends AbstractTrigger {
	private Integer timeInSeconds;

	public MultiThreadedTimeBasedTrigger(WeatherEngine engine, Integer timeInSeconds) {
		super(engine);
		this.timeInSeconds = timeInSeconds;
	}

	@Override
	public void start() {
		LocalTime endtime = LocalTime.now().plusSeconds(timeInSeconds);
		try {
			Thread t = new Thread(() -> {
				while (endtime.isAfter(LocalTime.now())) {
					notifyEngine();
					try {
						Thread.sleep(10);
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
