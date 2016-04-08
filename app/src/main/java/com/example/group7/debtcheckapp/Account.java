package com.example.group7.debtcheckapp;

/**
 * Created by niklasschluter on 08.04.16.
 */
public class Account {
    private String name;
    private String mail;
    private String password;
    public Account(String name, String mail, String password){
        this.name=name;
        this.mail=mail;
        this.password=password;
    }
    public String getName(){
        return this.name;
    }
    public String getMail(){
        return this.mail;
    }
    public String getPassword(){
        return this.password;
    }

}
