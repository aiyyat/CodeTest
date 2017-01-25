package com.technicalyorker.dbs.gorden.exception;

public class GordenException extends RuntimeException {
	private static final long serialVersionUID = -1626501420467939474L;

	public GordenException() {
	}

	public GordenException(String str) {
		super(str);
	}

	public GordenException(Throwable e) {
		super(e);
	}
}
