package com.technicalyorker.misc.commonwealthbank.weathersimulator.observers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.technicalyorker.misc.commonwealthbank.weathersimulator.domain.Weather;

/**
 * This simulates an observer accepting a weather object.
 * 
 * This particularly simulates the output described in the problem statement.
 * 
 * @author achuth
 *
 */
public class DateTimeBasedRealTimeSampleObserver implements WeatherObserver {
	private Weather w;
	private static final Logger logger = LoggerFactory.getLogger(DateTimeBasedAverageSampleObserver.class);

	public void updateChange(Weather w) {
		this.w = w;
		display();
	}

	@Override
	public void display() {
		logger.info(String.format("\n\t\t\t\t\t%s", w.toString()));
	}
}
