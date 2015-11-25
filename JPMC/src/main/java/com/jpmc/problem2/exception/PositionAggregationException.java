package com.jpmc.problem2.exception;

/**
 * 
 * @author achuth
 *
 */
public class PositionAggregationException extends RuntimeException {
	private static final long serialVersionUID = 6435738167449924050L;

	public PositionAggregationException() {
	}

	public PositionAggregationException(String msg) {
		super(msg);
	}
}
