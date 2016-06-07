package com.example.group7.debtcheckapp.Mock;

import java.io.Serializable;
//import java.math.BigDecimal;

public class Debt implements Serializable {

    private static final long serialVersionUID = 1L;
    private static int lastID=0;

    private int id;
    private double amount;
    private String reason;
    private String debtor;
    private String creditor;


    public Debt(String debtor, String creditor, double amount, String reason) {
        this.id = ++lastID;
        this.amount = amount;
        this.debtor = debtor;
        this.creditor = creditor;
        this.reason = reason;
        //this.owner = owner;
        //this.owner.addNewDebt(this);
    }

    public int getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    /*public Account getOwner() {
        return owner;
    }*/

    public String getReason() {
        return reason;
    }

    public String getDebtor() {
        return debtor;
    }

    public String getCreditor() {
        return creditor;
    }

    public void increase(double amount) {
        this.amount = this.amount + amount;
    }

    public void decrease(double amount) {
        this.amount = this.amount - amount;
    }

    public String toString() {
        return "Debt " + this.id + " (Amount=" + this.amount + ", Owner=" + this.debtor + ")";
    }

}

