package com.technicalyorker.misc.commonwealthbank.weathersimulator.exception;

/**
 * Describes an exception thrown by the system while Data Sample in invalid
 * 
 * @author achuth
 *
 */
public class WeatherDataSampleException extends WeatherSimulatorException {
	private static final long serialVersionUID = -5604596875398926690L;

	public WeatherDataSampleException(Throwable t) {
		super(t);
	}

	public WeatherDataSampleException(String msg) {
		super(msg);
	}

}
