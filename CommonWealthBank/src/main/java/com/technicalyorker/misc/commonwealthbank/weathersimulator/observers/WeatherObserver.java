package com.technicalyorker.misc.commonwealthbank.weathersimulator.observers;

import com.technicalyorker.misc.commonwealthbank.weathersimulator.domain.Weather;

/**
 * Observer are registered against an Emitter and listens to them for a
 * notification to change of state
 * 
 * Observers are usually a Set of listeners with callbacks registered within an
 * Emitter. Each Observer does one or similar activities like notifying the user
 * in the situation where weather exceeds a defined threshold.
 * 
 * @author achuth
 *
 */
public interface WeatherObserver {
	/**
	 * The observer implementation is notified of a change in state. A new data
	 * sample is the example of change in state
	 * 
	 * @param w
	 */
	public void updateChange(Weather w);

	/**
	 * This is called at the end of the calculation. This is particularly useful
	 * if the observer does aggregation or grouping calculations on the data
	 * samples.
	 */
	public void display();
}
