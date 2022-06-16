package com.tsdl.practices.exception;

public class CalculateException extends RuntimeException {

    public CalculateException() {

    }

    public CalculateException(CalculateExceptionType type) {

    }

    public CalculateException(String msg) {

    }

    public static enum CalculateExceptionType {
        UNDEFINED
    }

}
