package com.technicalyorker.payroll.exception;

/**
 * Exception that would be thrown if issues related to writing to the source is
 * encountered.
 * 
 * @author achuth
 *
 */
public class OuputSourceWriteException extends PayRollException {
	private static final long serialVersionUID = -8199025220625755252L;

	public OuputSourceWriteException(Exception e) {
		super(e);
	}

	public OuputSourceWriteException(String msg) {
		super(msg);
	}
}
