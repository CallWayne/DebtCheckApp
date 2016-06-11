package com.example.group7.debtcheckapp.Exceptions;

/**
 * Exception NoSessionException
 * @author Niklas Schlüter, André Käthner
 * @version 1.0
 */
public class NoSessionException extends Exception {

    /**
     * Konstruktor
     * @param message String
     * @return NoSessionException
     */
    public NoSessionException(String message) {
        super(message);
    }

    /**
     * Konstruktor
     * @param message String
     * @param throwable Throwable
     * @return NoSessionException
     */
    public NoSessionException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
