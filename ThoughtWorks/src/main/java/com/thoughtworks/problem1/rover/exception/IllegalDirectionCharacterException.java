package com.thoughtworks.problem1.rover.exception;

public class IllegalDirectionCharacterException extends RuntimeException {
	private static final long serialVersionUID = 5912217470241898067L;

	public IllegalDirectionCharacterException(char dir) {
		super("" + dir + " is a Valid Character");
	}
}
