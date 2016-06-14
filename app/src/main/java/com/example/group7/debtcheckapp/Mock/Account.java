package com.example.group7.debtcheckapp.Mock;

/**
 * Mock Objekt für die Accounts
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Niklas Schlüter, André Käthner
 * @serial Account
 * @version 1.0
 */
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;
    private static int lastID = 0;

    private int id;
    private String userName;
    private String email;
    private String password;
    private HashMap<Integer,Debt> debts;
    private ArrayList<Account> friendlist;

    /**
     * Konstruktor
     * @param userName String
     * @param email String
     * @param password String
     */
    public Account (String userName, String email, String password){
        this.id = lastID++;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.debts = new HashMap<>();
        this.friendlist = new ArrayList<>();
    }
    /**
     * Methode für das Hinzufügen einer neuen Schuld
     * @param newDebt Debt
     */
    public void addNewDebt(Debt newDebt) {
        this.debts.put(newDebt.getId(), newDebt);
    }

    /**
     * GET-Methode für Username
     * @return String username
     */
    public String getUserName() {
        return this.userName;
    }

    /**
     * GET-Methode für Password
     * @return String password
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * GET-Methode für Email
     * @return String email
     */
    public String getEmail(){
        return this.email;
    }

    /**
     * GET-Methode für eine bestimmte Schuld
     * @param debtId int
     * @return Debt
     */
    public Debt getDebtById(int debtId) {
        return debts.get(debtId);
    }

    /**
     * GET-Methode für alle Schulden
     * @return List
     */
    public List<Debt> getDebts() {
        return new ArrayList<Debt>(debts.values());
    }

    /**
     * GET-Methode für ID des Accounts
     * @return int
     */
    public int getId() {
        return id;
    }

    /**
     * SET-Methode für ID des Accounts
     * @param accountId int
     */
    public void setId(int accountId) {
        this.id = accountId;
    }

    /**
     * Methode fürs adden von Freunden
     * @param newAccount Account
     */
    public void addFriendlist(Account newAccount){
        friendlist.add(newAccount);
    }

    /**
     * GET-Methode für alle Freunde
     * @preturn ArrayList
     */
    public ArrayList<Account> getFriendlist(){
        return this.friendlist;
    }

    /**
     * GET-Methode für den Namen des Freund
     * @return String
     */
    public String getFriendname(){
        for(int i = 0; i<friendlist.size();i++){
            Account x = friendlist.get(i);
            String y = x.getUserName();
            return y;
        }
        return null;
    }

    /**
     * toString()-Methode für die Klasse Account
     * @return String
     */
    public String toString() {
        return "Account (" + this.getId() + "): " + this.getUserName();
    }
}

