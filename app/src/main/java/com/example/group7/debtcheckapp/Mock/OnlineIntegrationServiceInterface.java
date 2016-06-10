package com.example.group7.debtcheckapp.Mock;

/**
 * Interface für die OnlineIntegration WebService
 */

import com.example.group7.debtcheckapp.Exceptions.InvalidAddNewDebtException;
import com.example.group7.debtcheckapp.Exceptions.InvalidLoginException;
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * @author Niklas Schlüter, André Käthner
 * @version 1.0
 */
public interface OnlineIntegrationServiceInterface {

    /**
     * Methode für das Login
     * @param email String
     * @param password String
     * @return Account
     * @exception InvalidLoginException
     */
    public Account login(String email, String password) throws InvalidLoginException;

    /**
     * Methode für das Logout
     */
    public void logout();

    /**
     * Methode für das Registrieren
     * @param username String
     * @param email String
     * @param password String
     * @return Account
     * @exception InvalidLoginException
     */
    public Account signup(String username, String email, String password)throws InvalidLoginException;

    /**
     * Methode für das adden einer neuen Schuld
     * @param username String
     * @param amount BigDecimal
     * @param reason String
     * @return Debt
     * @exception InvalidAddNewDebtException
     */
    public Debt addNewDebt(String username, BigDecimal amount, String reason) throws InvalidAddNewDebtException;

    /**
     * GET-Methode für alle Schulden
     * @return ArrayList
     */
    public ArrayList<Debt> getAllDebts();

    /**
     * GET-Methode für alle Forderungen
     * @return ArrayList
     */
    public ArrayList<Debt> getAllClaims();

    /**
     * Methode zum zurückzahlen einer Schuld
     * @param creditor String
     * @param amount BigDecimal
     * @param id int
     */
    public void payDebt(String creditor, BigDecimal amount, int id);

}