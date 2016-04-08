package com.example.group7.debtcheckapp;

import java.util.ArrayList;

/**
 * Created by niklasschluter on 08.04.16.
 */
public class AccountService {

    private ArrayList<Account> AccountList = new ArrayList<>();

    public boolean login(String mail, String password){
        Account q = new Account("testuser", "test@test.de", "123456");
        AccountList.add(q);
        for(int i = 0; i <= AccountList.size(); i++){
            Account x = AccountList.get(i);
            if((x.getMail().equals(mail))&&(x.getPassword().equals(password))){
                return true;
            }
            else{
                return false;
            }
        }
        return false;
    }

    public void signup(String name, String mail, String password){
        AccountList.add(new Account(name, mail, password));
    }

    public int getSize(){
        return AccountList.size();
    }
}
