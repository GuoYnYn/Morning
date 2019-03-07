package com.mec.morning.core;

public class IntercepterAlreadyExistException extends Exception {
	private static final long serialVersionUID = 6790855311912955894L;

	public IntercepterAlreadyExistException() {
		super();
	}

	public IntercepterAlreadyExistException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public IntercepterAlreadyExistException(String message, Throwable cause) {
		super(message, cause);
	}

	public IntercepterAlreadyExistException(String message) {
		super(message);
	}

	public IntercepterAlreadyExistException(Throwable cause) {
		super(cause);
	}

}
