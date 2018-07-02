package com.ciel.appoint.exception;

/**
 * Author: CIEL
 * Date: 2018/06
 */
public class RepeatAppoint extends RuntimeException {
    public RepeatAppoint(String message) {
		super(message);
	}

	public RepeatAppoint(String message, Throwable cause) {
		super(message, cause);
	}
}
