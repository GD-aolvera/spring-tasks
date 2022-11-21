package com.gd.clinic.exception;

public class AdminNotFoundException extends Exception{
    private static final long serialVersionUID = 1L;

    public AdminNotFoundException(String message) {
        super(message);
    }

    public AdminNotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
