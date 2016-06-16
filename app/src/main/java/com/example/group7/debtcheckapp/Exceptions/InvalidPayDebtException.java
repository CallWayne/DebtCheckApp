package com.example.group7.debtcheckapp.Exceptions;

/**
 * Exception InvalidPayDebtException
 * @author Niklas Schlüter, André Käthner
 * @version 1.0
 */
public class InvalidPayDebtException extends Exception {

    /**
     * Konstruktor
     * @param message String
     */
    public InvalidPayDebtException(String message) {
        super(message);
    }

    /**
     * Konstruktor
     */
    public InvalidPayDebtException() {
        super("PayDebt fehlgeschlagen!");
    }

    /**
     * Konstruktor
     * @param message String
     * @param throwable Throwable
     */
    public InvalidPayDebtException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
