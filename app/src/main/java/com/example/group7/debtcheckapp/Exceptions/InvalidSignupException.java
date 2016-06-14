package com.example.group7.debtcheckapp.Exceptions;

/**
 * Exception InvalidLoginException
 * @author Niklas Schlüter, André Käthner
 * @version 1.0
 */
public class InvalidSignupException extends Exception {

    /**
     * Konstruktor
     * @param message String
     */
    public InvalidSignupException(String message) {
        super(message);
    }

    /**
     * Konstruktor
     * @param message String
     * @param throwable Throwable
     */
    public InvalidSignupException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
