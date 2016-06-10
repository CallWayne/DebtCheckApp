package com.example.group7.debtcheckapp.Mock;

/**
 * Mock Objekt für die Schuld
 */

import java.io.Serializable;

/**
 * @author Niklas Schlüter, André Käthner
 * @serial Debt
 * @version 1.0
 */
public class Debt implements Serializable {

    private static final long serialVersionUID = 1L;
    private static int lastID=0;

    private int id;
    private double amount;
    private String reason;
    private String debtor;
    private String creditor;

    /**
     * Kontruktor
     * @param debtor String
     * @param creditor String
     * @param amount double
     * @param reason String
     */
    public Debt(String debtor, String creditor, double amount, String reason) {
        this.id = ++lastID;
        this.amount = amount;
        this.debtor = debtor;
        this.creditor = creditor;
        this.reason = reason;
    }

    /**
     * GET-Methode für die ID
     * @return int
     */
    public int getId() {
        return id;
    }

    /**
     * SET-Methode für die ID
     * @param id int
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * GET-Methode für Amount
     * @return double
     */
    public double getAmount() {
        return amount;
    }

    /**
     * GET-Methode für Reason
     * @return String
     */
    public String getReason() {
        return reason;
    }

    /**
     * GET-Methode für Debtor
     * @return String
     */
    public String getDebtor() {
        return debtor;
    }

    /**
     * GET-Methode für Creditor
     * @return String
     */
    public String getCreditor() {
        return creditor;
    }

    /**
     * Methode für erhöhen der Schuld
     * @param amount double
     */
    public void increase(double amount) {
        this.amount = this.amount + amount;
    }

    /**
     * Methode für verringern der Schuld
     * @param amount double
     */
    public void decrease(double amount) {
        this.amount = this.amount - amount;
    }

    /**
     * toString()-Methode der Klasse Debt
     * @return String
     */
    public String toString() {
        return "Debt " + this.id + " (Amount=" + this.amount + ", Owner=" + this.debtor + ")";
    }

}

