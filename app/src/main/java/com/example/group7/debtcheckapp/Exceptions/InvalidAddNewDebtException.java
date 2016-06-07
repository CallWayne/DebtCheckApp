package com.example.group7.debtcheckapp.Exceptions;


public class InvalidAddNewDebtException extends Exception {

    public InvalidAddNewDebtException(String message) {
        super(message);
    }

    public InvalidAddNewDebtException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
