package com.example.group7.debtcheckapp.Exceptions;

/**
 * Exception InvalidLoginException
 * @author Niklas Schlüter, André Käthner
 * @version 1.0
 */
public class InvalidLoginException extends Exception {

    /**
     * Konstruktor
     * @param message String
     * @return InvalidLoginException
     */
    public InvalidLoginException(String message) {
        super(message);
    }

    /**
     * Konstruktor
     * @param message String
     * @param throwable Throwable
     * @return InvalidLoginException
     */
    public InvalidLoginException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
