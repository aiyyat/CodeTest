package com.technicalyorker.calculator.exception;

import com.technicalyorker.calculator.exception.CalculatorException;

/**
 * 
 * @author achuth
 *
 */
public class IllegalCommandOperationException extends CalculatorException {
	private static final long serialVersionUID = -2646078183515294390L;

	public IllegalCommandOperationException(String msg) {
		super(msg);
	}
}
