package com.technicalyorker.calculator.exception;

/**
 * 
 * @author achuth
 *
 */
public class InvalidPostFixExpressionException extends CalculatorException {
	private static final long serialVersionUID = -3285362037707834219L;

	public InvalidPostFixExpressionException(String msg) {
		super(msg);
	}
}
