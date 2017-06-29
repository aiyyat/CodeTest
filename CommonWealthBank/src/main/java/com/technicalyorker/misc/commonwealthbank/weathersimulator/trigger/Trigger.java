package com.technicalyorker.misc.commonwealthbank.weathersimulator.trigger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.technicalyorker.misc.commonwealthbank.weathersimulator.observers.DateTimeBasedAverageSampleObserver;

/**
 * Trigger Interface has to be started using the start operation. Once started
 * it repeatedly calls the notifyEngine method that indicates to the Engine to
 * sample and emit data.
 * 
 * @author achuth
 *
 */
public interface Trigger<T> {
	final Logger logger = LoggerFactory.getLogger(DateTimeBasedAverageSampleObserver.class);

	public void start();

	/**
	 * Trigger informs the engine to sample data. Implementation could be
	 * anything from a simple scheduler based trigger to more complex
	 * implementations such as notification of weather data change by a
	 * transducer.
	 * 
	 * @param t
	 */
	public void notifyEngine(T t);

	/**
	 * Simple log notification of the start and successful completion of the
	 * triggering exercise.
	 */
	public default void triggerStartMessage() {
		logger.debug("\n[Data Sampling Kick Off - Done!]\n");
	}

	public default void triggerCompletedMessage() {
		logger.debug("\n[Data Sampling - Completed!]\n");
	}
}