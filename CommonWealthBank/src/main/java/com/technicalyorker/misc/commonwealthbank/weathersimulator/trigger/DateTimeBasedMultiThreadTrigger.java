package com.technicalyorker.misc.commonwealthbank.weathersimulator.trigger;

import java.time.LocalDateTime;

import com.technicalyorker.misc.commonwealthbank.weathersimulator.constants.WeatherSimulatorConstants;
import com.technicalyorker.misc.commonwealthbank.weathersimulator.engine.WeatherEngine;

/**
 * This class starts a thread that indicates to the Engine in a defined
 * timeInterval to perform sampling. The timeInSeconds indicate how long the
 * trigger must run.
 * 
 * @author achuth
 *
 */
public class DateTimeBasedMultiThreadTrigger extends AbstractTrigger<LocalDateTime> {

	private Integer endTimeInMinutes;
	private LocalDateTime startTime;

	public DateTimeBasedMultiThreadTrigger(WeatherEngine<LocalDateTime> engine, LocalDateTime startTime,
			Integer endTimeInMinutes) {
		super(engine);
		this.startTime = startTime;
		this.endTimeInMinutes = endTimeInMinutes;
	}

	@Override
	public void start() {
		try {
			Thread t = new Thread(() -> {
				LocalDateTime runningTime = startTime;
				LocalDateTime endTime = runningTime.plusMinutes(endTimeInMinutes);
				while (endTime.isAfter(runningTime)) {
					notifyEngine(runningTime);
					runningTime = runningTime.plusMinutes(WeatherSimulatorConstants.INTERVAL_IN_MINUTES);
				}
			});
			t.start();
			triggerStartMessage();
			t.join();
			triggerCompletedMessage();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
