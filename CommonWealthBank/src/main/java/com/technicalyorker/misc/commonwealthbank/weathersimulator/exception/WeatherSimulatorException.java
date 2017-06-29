package com.technicalyorker.misc.commonwealthbank.weathersimulator.exception;

/**
 * Describes all Weather Exceptions thrown by the System.
 * 
 * @author achuth
 *
 */
public class WeatherSimulatorException extends RuntimeException {
	private static final long serialVersionUID = -5456020858095057856L;

	public WeatherSimulatorException(Throwable t) {
		super(t);
	}

	public WeatherSimulatorException(String msg) {
		super(msg);
	}
}
