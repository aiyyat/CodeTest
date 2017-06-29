package com.technicalyorker.misc.commonwealthbank.weathersimulator;

import static com.technicalyorker.misc.commonwealthbank.weathersimulator.constants.WeatherSimulatorConstants.INTERVAL_IN_MINUTES;
import static com.technicalyorker.misc.commonwealthbank.weathersimulator.constants.WeatherSimulatorConstants.NO_OF_DATA_PTS;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.technicalyorker.misc.commonwealthbank.weathersimulator.emitter.DateTimeBasedWeatherDataEmitterSubject;
import com.technicalyorker.misc.commonwealthbank.weathersimulator.engine.DateTimeBasedWeatherSimulatorEngine;
import com.technicalyorker.misc.commonwealthbank.weathersimulator.observers.DateTimeBasedAverageSampleObserver;
import com.technicalyorker.misc.commonwealthbank.weathersimulator.observers.DateTimeBasedMaxMinTemperatureSampleObserver;
import com.technicalyorker.misc.commonwealthbank.weathersimulator.observers.DateTimeBasedPreditionAnalyticsObserver;
import com.technicalyorker.misc.commonwealthbank.weathersimulator.observers.DateTimeBasedRealTimeSampleObserver;
import com.technicalyorker.misc.commonwealthbank.weathersimulator.observers.DateTimeBasedTemperatureThresholdWarningSimulatorObserver;
import com.technicalyorker.misc.commonwealthbank.weathersimulator.observers.WeatherObserver;
import com.technicalyorker.misc.commonwealthbank.weathersimulator.trigger.DateTimeBasedMultiThreadTrigger;
import com.technicalyorker.misc.commonwealthbank.weathersimulator.util.WeatherSimulatorUtil;

/**
 * A Weather Simulator Application needs an instance of a Weather Engine and a
 * trigger. A trigger is any class that implements Trigger and indicates to the
 * Engine when to perform Data Sampling.
 * 
 * @author achuth
 *
 */
public class WeatherSimulatorApplication {
	private static final Logger logger = LoggerFactory.getLogger(DateTimeBasedAverageSampleObserver.class);

	public static void main(String[] args) {
		WeatherObserver latest = new DateTimeBasedRealTimeSampleObserver();
		WeatherObserver average = new DateTimeBasedAverageSampleObserver();
		WeatherObserver temperature = new DateTimeBasedMaxMinTemperatureSampleObserver();
		WeatherObserver threshold = new DateTimeBasedTemperatureThresholdWarningSimulatorObserver();
		DateTimeBasedWeatherDataEmitterSubject emitter = new DateTimeBasedWeatherDataEmitterSubject();
		DateTimeBasedPreditionAnalyticsObserver analytics = new DateTimeBasedPreditionAnalyticsObserver(48);
		emitter.registerObserver(latest);
		emitter.registerObserver(average);
		emitter.registerObserver(temperature);
		emitter.registerObserver(threshold);
		emitter.registerObserver(analytics);

		DateTimeBasedWeatherSimulatorEngine engine = new DateTimeBasedWeatherSimulatorEngine(emitter);
		LocalDateTime startTime = WeatherSimulatorUtil.format("2016-10-11T20:00:00Z");
		new DateTimeBasedMultiThreadTrigger(engine, startTime, NO_OF_DATA_PTS * INTERVAL_IN_MINUTES).start();
		logger.info("\n\nFinal Stats:\n------------");
		average.display();
		temperature.display();
		analytics.display();
		logger.info("\n");
	}
}
