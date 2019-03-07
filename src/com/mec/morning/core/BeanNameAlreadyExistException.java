package com.mec.morning.core;

public class BeanNameAlreadyExistException extends Exception {
	private static final long serialVersionUID = -8473856425724572174L;

	public BeanNameAlreadyExistException() {
	}

	public BeanNameAlreadyExistException(String message) {
		super(message);
	}

	public BeanNameAlreadyExistException(Throwable cause) {
		super(cause);
	}

	public BeanNameAlreadyExistException(String message, Throwable cause) {
		super(message, cause);
	}

	public BeanNameAlreadyExistException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
