package com.example.group7.debtcheckapp.Exceptions;

/**
 * Exception InvalidLogoutException
 * @author Niklas Schlüter, André Käthner
 * @version 1.0
 */
public class InvalidLogoutException extends Exception {

    /**
     * Konstruktor
     * @param message String
     */
    public InvalidLogoutException(String message) {
        super(message);
    }

    /**
     * Konstruktor
     */
    public InvalidLogoutException() {
        super("Logout fehlgeschlagen!");
    }

    /**
     * Konstruktor
     * @param message String
     * @param throwable Throwable
     */
    public InvalidLogoutException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
