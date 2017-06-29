package com.technicalyorker.misc.commonwealthbank.weathersimulator.exception;

/**
 * Exception thrown if the pre-calculation validation check of the input data
 * fails.
 * 
 * @author achuth
 *
 */
public class TimeSeriesInputDataException extends RuntimeException {
	private static final long serialVersionUID = -5456020858095057856L;

	public TimeSeriesInputDataException(Throwable t) {
		super(t);
	}

	public TimeSeriesInputDataException(String msg) {
		super(msg);
	}
}
