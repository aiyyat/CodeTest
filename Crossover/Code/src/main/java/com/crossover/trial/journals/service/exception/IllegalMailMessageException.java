package com.crossover.trial.journals.service.exception;

import com.crossover.trial.journals.exception.ServiceException;

public class IllegalMailMessageException extends ServiceException {

	private static final long serialVersionUID = -8470741614585399460L;

	public IllegalMailMessageException(String msg) {
		super(msg);
	}
}
