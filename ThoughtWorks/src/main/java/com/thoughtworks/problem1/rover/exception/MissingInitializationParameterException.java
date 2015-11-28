package com.thoughtworks.problem1.rover.exception;

public class MissingInitializationParameterException extends RuntimeException {

	private static final long serialVersionUID = 1674726619992170645L;

	public MissingInitializationParameterException(String string) {
		super(string);
	}

}
