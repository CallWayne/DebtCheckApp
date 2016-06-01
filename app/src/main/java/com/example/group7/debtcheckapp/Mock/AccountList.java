package com.example.group7.debtcheckapp.Mock;

import java.util.ArrayList;

/**
 * Created by niklasschluter on 17.05.16.
 */
public class AccountList {
    private static ArrayList<Account> AccList = new ArrayList<Account>();

    public static ArrayList<Account> getAccList(){
        return AccList;
    }

    public static void setAccList(Account newAcc){
        AccList.add(newAcc);
    }

}
