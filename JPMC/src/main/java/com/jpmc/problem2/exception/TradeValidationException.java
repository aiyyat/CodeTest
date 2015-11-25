package com.jpmc.problem2.exception;

/**
 * 
 * @author achuth
 *
 */
public class TradeValidationException extends PositionAggregationException {
	private static final long serialVersionUID = 3410570433877974539L;

	public TradeValidationException(String str) {
		super(str);
	}
}
