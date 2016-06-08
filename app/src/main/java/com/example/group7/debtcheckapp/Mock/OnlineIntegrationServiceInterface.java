package com.example.group7.debtcheckapp.Mock;

import com.example.group7.Wsdl2Code.OnlineIntegrationService.debtListResponse;
import com.example.group7.Wsdl2Code.OnlineIntegrationService.returnCodeResponse;
import com.example.group7.debtcheckapp.Exceptions.InvalidAddNewDebtException;
import com.example.group7.debtcheckapp.Exceptions.InvalidLoginException;
import com.example.group7.debtcheckapp.Exceptions.NoSessionException;

import java.math.BigDecimal;
import java.util.ArrayList;

public interface OnlineIntegrationServiceInterface {

    public Account login(String email, String password) throws InvalidLoginException;

    public void logout();

    public Account signup(String username, String email, String password)throws InvalidLoginException;

    public Debt addNewDebt(String username, BigDecimal amount, String reason) throws InvalidAddNewDebtException;

    public ArrayList<Debt> getAllDebts();

    public ArrayList<Debt> getAllClaims();

    public void payDebt(String creditor, BigDecimal amount, int id);

}