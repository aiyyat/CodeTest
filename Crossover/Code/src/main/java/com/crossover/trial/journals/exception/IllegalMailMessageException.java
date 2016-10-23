package com.crossover.trial.journals.exception;

public class IllegalMailMessageException extends ServiceException {

	private static final long serialVersionUID = -8470741614585399460L;

	public IllegalMailMessageException(String msg) {
		super(msg);
	}
}
