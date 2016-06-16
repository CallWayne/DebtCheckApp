package com.example.group7.debtcheckapp.Exceptions;

/**
 * Exception InvalidGetAllClaimsException
 * @author Niklas Schlüter, André Käthner
 * @version 1.0
 */
public class InvalidGetAllClaimsException extends Exception {

    /**
     * Konstruktor
     * @param message String
     */
    public InvalidGetAllClaimsException(String message) {
        super(message);
    }

    /**
     * Konstruktor
     */
    public InvalidGetAllClaimsException() {
        super("GetAllClaims fehlgeschlagen!");
    }

    /**
     * Konstruktor
     * @param message String
     * @param throwable Throwable
     */
    public InvalidGetAllClaimsException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
