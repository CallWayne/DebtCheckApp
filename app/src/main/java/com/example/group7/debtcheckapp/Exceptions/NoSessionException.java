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
     */
    public NoSessionException(String message) {
        super(message);
    }

    /**
     * Konstruktor
     */
    public NoSessionException() {
        super("NoSession fehlgeschlagen!");
    }

    /**
     * Konstruktor
     * @param message String
     * @param throwable Throwable
     */
    public NoSessionException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
