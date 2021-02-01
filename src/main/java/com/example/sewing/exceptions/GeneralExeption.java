package com.example.sewing.exceptions;

public class GeneralExeption extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public GeneralExeption(String message) {
		super(message);
	}

	@Override
	public synchronized Throwable fillInStackTrace() {
		return this;
	}
}
