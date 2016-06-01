package com.example.group7.debtcheckapp;

import android.app.Application;
import com.example.group7.debtcheckapp.Mock.Account;
import com.example.group7.debtcheckapp.Mock.OnlineIntegrationServiceInterface;

public class DebtCheckAndroidApplication extends Application {

    private Account account;
    private OnlineIntegrationServiceInterface onlineIntegrationService;

    public DebtCheckAndroidApplication() {
        this.onlineIntegrationService = new OnlineIntegrationServiceImplements();
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Account getAccount() {
        return this.account;
    }

    public OnlineIntegrationServiceInterface getOnlineIntegrationServiceInterface() {
        return this.onlineIntegrationService;
    }
}
