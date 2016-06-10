package com.example.group7.debtcheckapp.Mock;

/**
 * Mock Objekt für die Forderungen
 */

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Niklas Schlüter, André Käthner
 * @serial Claim
 * @version 1.0
 */
public class Claim implements Serializable {

    private static final long serialVersionUID = 1L;
    private static int lastID=0;

    private int id;
    private BigDecimal amount;
    private Account owner;

    /**
     * Konstruktor
     * @param owner Account
     * @param amount BigDecimal
     */
    public Claim(Account owner, BigDecimal amount) {
        this.id = ++lastID;
        this.amount = amount;
        this.owner = owner;
        this.owner.addNewClaim(this);
    }

    /**
     * GET-Methode für die ID
     * @return int
     */
    public int getId() {
        return id;
    }

    /**
     * GET-Methode für Amount
     * @return BigDecimal
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * GET-Methode für Owner
     * @return Account
     */
    public Account getOwner() {
        return owner;
    }

    /**
     * Methode zum erhöhen der Forderung
     * @param amount BigDecimal
     */
    public void increase(BigDecimal amount) {
        this.amount = this.amount.add(amount);
    }

    /**
     * Methode zum verrringern der Forderung
     * @param amount BigDecimal
     */
    public void decrease(BigDecimal amount) {
        this.amount = this.amount.subtract(amount);
    }

    /**
     * toString()-Methode für die Klasse Claim
     * @return String
     */
    public String toString() {
        return "Debt " + this.id + " (Amount=" + this.amount + ", Owner=" + this.getOwner().getUserName() + ")";
    }

}




