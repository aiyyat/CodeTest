package com.technicalyorker.calculator.expression.definition.exception;

import com.technicalyorker.calculator.exception.CalculatorException;

public class IllegalCommandOperationException extends CalculatorException {
	private static final long serialVersionUID = -2646078183515294390L;

	public IllegalCommandOperationException(String msg) {
		super(msg);
	}
}
