package com.technicalyorker.misc.commonwealthbank.weathersimulator.exception;

/**
 * Thrown typically when Service times out trying to pull data from source.
 * 
 * @author achuth
 *
 */
public class WeatherSimulatorTimeoutException extends WeatherSimulatorException {
	private static final long serialVersionUID = -5604596875398926690L;

	public WeatherSimulatorTimeoutException(Throwable t) {
		super(t);
	}

	public WeatherSimulatorTimeoutException(String msg) {
		super(msg);
	}

}
