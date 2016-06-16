package com.example.group7.debtcheckapp.Exceptions;

/**
 * Exception InvalidGetAllDebtsException
 * @author Niklas Schlüter, André Käthner
 * @version 1.0
 */
public class InvalidGetAllDebtsException extends Exception {

    /**
     * Konstruktor
     * @param message String
     */
    public InvalidGetAllDebtsException(String message) {
        super(message);
    }

    /**
     * Konstruktor
     */
    public InvalidGetAllDebtsException() {
        super("GetAllDebts fehlgeschlagen");
    }

    /**
     * Konstruktor
     * @param message String
     * @param throwable Throwable
     */
    public InvalidGetAllDebtsException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
