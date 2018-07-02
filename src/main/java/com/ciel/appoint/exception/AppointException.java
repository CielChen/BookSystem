package com.ciel.appoint.exception;

/**
 * Author: CIEL
 * Date: 2018/06
 */
public class AppointException extends RuntimeException {
    public AppointException(String message) {
        super(message);
    }

    public AppointException(String message, Throwable cause) {
        super(message, cause);
    }
}

