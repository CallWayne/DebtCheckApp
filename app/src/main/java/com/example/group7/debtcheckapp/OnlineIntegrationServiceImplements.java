package com.example.group7.debtcheckapp;

import com.example.group7.Wsdl2Code.OnlineIntegrationService.OnlineIntegrationService;
import com.example.group7.Wsdl2Code.OnlineIntegrationService.addNewDebtResponsee;
import com.example.group7.Wsdl2Code.OnlineIntegrationService.debtListResponse;
import com.example.group7.Wsdl2Code.OnlineIntegrationService.payDebtResponsee;
import com.example.group7.Wsdl2Code.OnlineIntegrationService.userLoginResponse;

import com.example.group7.debtcheckapp.Mock.Account;
import com.example.group7.debtcheckapp.Mock.Debt;

import com.example.group7.debtcheckapp.Exceptions.InvalidLoginException;
import com.example.group7.debtcheckapp.Exceptions.InvalidAddNewDebtException;

import com.example.group7.debtcheckapp.Mock.OnlineIntegrationServiceInterface;

import java.math.BigDecimal;
import java.util.ArrayList;



public class OnlineIntegrationServiceImplements implements OnlineIntegrationServiceInterface {

    private OnlineIntegrationService webService;
    private int sessionId;

    public OnlineIntegrationServiceImplements() {
        this.webService = new OnlineIntegrationService();
    }

    @Override
    public Account login(String email, String password) throws InvalidLoginException {
        userLoginResponse response = this.webService.login(email, password);
        if (response.returnCodeField != 0)
            throw new InvalidLoginException("Login not successful");
        this.sessionId = response.sessionId;
        return new Account(response.account.userName, response.account.password ,response.account.email);
    }

    @Override
    public void logout()  {
        webService.logout(this.sessionId);
    }

    @Override
    public Account signup(String username, String email, String password) {
        userLoginResponse response = this.webService.registerNewAccount(username, email, password);
        this.sessionId = response.sessionId;
        return new Account(response.account.userName, response.account.email ,response.account.password);
    }

    @Override
    public Debt addNewDebt(String username, BigDecimal amount, String reason) throws InvalidAddNewDebtException {
        addNewDebtResponsee response = this.webService.addNewDebt(this.sessionId, username, amount.doubleValue(), reason);
        if (response.returnCodeField != 0)
            throw new InvalidAddNewDebtException("Add a new Debt not succesful");
        return new Debt(response.debt.debtor, response.debt.creditor, response.debt.amount, response.debt.reason);
    }

    @Override
    public ArrayList<Debt> getAllDebts(){
        debtListResponse response = this.webService.getMyDebts(this.sessionId);
        if(response.debtList == null){
            return null;
        }
        ArrayList debtList = new ArrayList<Debt>();
        for(int i = 0; i < response.debtList.size(); i++) {
            Debt debt = new Debt(response.debtList.get(i).debtor, response.debtList.get(i).creditor, response.debtList.get(i).amount, response.debtList.get(i).reason);
            debt.setId(response.debtList.get(i).id);
            debtList.add(debt);
        }
        return debtList;
    }

    @Override
    public ArrayList<Debt> getAllClaims(){
        debtListResponse response = this.webService.getMyClaims(this.sessionId);
        if(response.debtList == null){
            return null;
        }
        ArrayList claimList = new ArrayList<Debt>();
        for(int i = 0; i < response.debtList.size(); i++) {
            Debt claim = new Debt(response.debtList.get(i).debtor, response.debtList.get(i).creditor, response.debtList.get(i).amount, response.debtList.get(i).reason);
            claimList.add(claim);
        }
        return claimList;
    }
    @Override
    public void payDebt(String creditor, BigDecimal amount, int id){
        this.webService.payDebt(this.sessionId, creditor, amount.doubleValue(), id);
    }
}
