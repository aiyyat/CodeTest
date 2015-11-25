package com.jpmc.problem2.exception;

/**
 * 
 * @author achuth
 *
 */
public class CommandException extends PositionAggregationException {
	private static final long serialVersionUID = 169288330723172303L;

	public CommandException(String msg) {
		super(msg);
	}
}
