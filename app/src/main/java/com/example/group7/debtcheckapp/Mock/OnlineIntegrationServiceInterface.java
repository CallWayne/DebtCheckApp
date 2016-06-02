package com.example.group7.debtcheckapp.Mock;

import com.example.group7.debtcheckapp.Exceptions.InvalidLoginException;
import com.example.group7.debtcheckapp.Exceptions.NoSessionException;

public interface OnlineIntegrationServiceInterface {

    public Account login(String username, String password) throws InvalidLoginException;

    public void logout() throws NoSessionException;

    public Account signup(String username, String email, String password)throws InvalidLoginException;
}