package com.example.group7.debtcheckapp.Exceptions;

/**
 * Exception InvalidAddNewDebtException
 * @author Niklas Schlüter, André Käthner
 * @version 1.0
 */
public class InvalidAddNewDebtException extends Exception {

    /**
     * Konstruktor
     * @param message String
     * @return InvalidNewDebtException
     */
    public InvalidAddNewDebtException(String message) {
        super(message);
    }

    /**
     * Konstruktor
     * @param message String
     * @param throwable Throwable
     * @return InvalidNewDebtException
     */
    public InvalidAddNewDebtException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
