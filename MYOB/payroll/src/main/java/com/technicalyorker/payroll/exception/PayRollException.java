package com.technicalyorker.payroll.exception;

/**
 * Root Exception
 * 
 * @author achuth
 *
 */
public class PayRollException extends RuntimeException {
	private static final long serialVersionUID = 2745344476723108171L;

	PayRollException(Exception e) {
		super(e);
	}

	public PayRollException(String msg) {
		super(msg);
	}
}
