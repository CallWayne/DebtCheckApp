package com.example.group7.debtcheckapp.Exceptions;


public class NoSessionException extends Exception {

    public NoSessionException(String message) {
        super(message);
    }

    public NoSessionException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
