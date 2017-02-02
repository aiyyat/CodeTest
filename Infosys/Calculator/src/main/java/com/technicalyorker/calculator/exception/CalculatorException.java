package com.technicalyorker.calculator.exception;

/**
 * Root of all Application & Business Exceptions
 * 
 * @author achuth
 *
 */
public class CalculatorException extends RuntimeException {
	private static final long serialVersionUID = 4309946770344956080L;

	public CalculatorException(String msg) {
		super(msg);
	}
}
