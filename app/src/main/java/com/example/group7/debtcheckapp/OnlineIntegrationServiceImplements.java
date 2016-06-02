package com.example.group7.debtcheckapp;

import com.example.group7.Wsdl2Code.OnlineIntegrationService.OnlineIntegrationService;
import com.example.group7.Wsdl2Code.OnlineIntegrationService.userLoginResponse;
import com.example.group7.Wsdl2Code.OnlineIntegrationService.returnCodeResponse;

import com.example.group7.debtcheckapp.Mock.Account;
import com.example.group7.debtcheckapp.Mock.AccountList;
import com.example.group7.debtcheckapp.Mock.Claim;
import com.example.group7.debtcheckapp.Mock.Debt;

import com.example.group7.debtcheckapp.Exceptions.InvalidLoginException;
import com.example.group7.debtcheckapp.Exceptions.NoSessionException;
import com.example.group7.debtcheckapp.Mock.OnlineIntegrationServiceInterface;

public class OnlineIntegrationServiceImplements implements OnlineIntegrationServiceInterface {

    private OnlineIntegrationService webService;
    private int sessionId;

    public OnlineIntegrationServiceImplements() {
        this.webService = new OnlineIntegrationService();
    }

    @Override
    public Account login(String username, String password) throws InvalidLoginException {
        userLoginResponse response = this.webService.login(username, password);
        if (response.returnCodeField != 0)
            throw new InvalidLoginException("Logout not successful");
        this.sessionId = response.sessionId;
        return new Account(response.account.userName, response.account.password ,response.account.email);
    }

    @Override
    public void logout() throws NoSessionException {
        returnCodeResponse response = this.webService.logout(this.sessionId);
        if (response.returnCodeField != 0)
            throw new NoSessionException("Logout not successful");
    }
}
