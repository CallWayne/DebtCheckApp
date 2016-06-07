package com.example.group7.debtcheckapp.Mock;

import com.example.group7.debtcheckapp.Exceptions.InvalidAddNewDebtException;
import com.example.group7.debtcheckapp.Exceptions.InvalidLoginException;
import com.example.group7.debtcheckapp.Exceptions.NoSessionException;

import java.math.BigDecimal;

public interface OnlineIntegrationServiceInterface {

    public Account login(String email, String password) throws InvalidLoginException;

    public void logout() throws NoSessionException;

    public Account signup(String username, String email, String password)throws InvalidLoginException;

    public Debt addNewDebt(int sessionId, String username, double amount, String reason) throws InvalidAddNewDebtException;
}