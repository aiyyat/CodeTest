package com.technicalyorker.payroll.exception;

/**
 * Any Exception that is caused due to reading from input source. e.g. incorrect
 * datatype
 * 
 * @author achuth
 *
 */
public class InputSourceReadException extends PayRollException {
	private static final long serialVersionUID = -8199025220625755252L;

	public InputSourceReadException(Exception e) {
		super(e);
	}

	public InputSourceReadException(String msg) {
		super(msg);
	}
}
