package com.example.group7.debtcheckapp.Mock;

/**
 * Mock Objekt für Simulation der Freundesliste
 */

import java.util.ArrayList;

/**
 * @author Niklas Schlüter, André Käthner
 * @version 1.0
 */
public class AccountList {

    private static ArrayList<Account> AccList = new ArrayList<Account>();

    /**
     * GET-Methode für die AccountList
     * @return ArrayList
     */
    public static ArrayList<Account> getAccList(){
        return AccList;
    }

    /**
     * SET-Methode für die AccountList
     * @param newAcc Account
     */
    public static void setAccList(Account newAcc){
        AccList.add(newAcc);
    }

}
