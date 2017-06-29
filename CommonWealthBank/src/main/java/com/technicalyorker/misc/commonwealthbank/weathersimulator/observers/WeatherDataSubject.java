package com.technicalyorker.misc.commonwealthbank.weathersimulator.observers;

import java.util.ArrayList;
import java.util.List;

import com.technicalyorker.misc.commonwealthbank.weathersimulator.domain.Weather;
import com.technicalyorker.misc.commonwealthbank.weathersimulator.observers.WeatherObserver;

/**
 * This is a sample implementation of Subject of the Observer pattern. Typically
 * one could have used the Observable/Observer provide in java.util to achieve
 * the same had it not been a code test.
 * 
 * @author achuth
 *
 */
public abstract class WeatherDataSubject {
	private List<WeatherObserver> observers = new ArrayList<WeatherObserver>();

	/**
	 * Used to register Observers which will be notified of a new in weather
	 * sample.
	 * 
	 * @param w
	 */
	public void registerObserver(WeatherObserver w) {
		observers.add(w);
	}

	/**
	 * This operation updates all the registered observers of the new weather
	 * sample so that Different observers can perform their respective operation
	 * using the data.
	 * 
	 * @param w
	 */
	public void updateChange(Weather w) {
		observers.stream().forEach(c -> c.updateChange(w));
	}
}
