package com.example.group7.debtcheckapp;

/**
 * Klasse für eine AndroidApplication
 */

import android.app.Application;
import com.example.group7.debtcheckapp.Mock.Account;
import com.example.group7.debtcheckapp.Mock.Debt;
import com.example.group7.debtcheckapp.Mock.OnlineIntegrationServiceInterface;

/**
 * @author Niklas Schlüter, André Käthner
 * @version 1.0
 */
public class DebtCheckAndroidApplication extends Application {

    private Account account;
    private Debt debt;
    private OnlineIntegrationServiceInterface onlineIntegrationService;

    /**
     * Konstruktor
     */
    public DebtCheckAndroidApplication() {
        this.onlineIntegrationService = new OnlineIntegrationServiceImplements();
    }

    /**
     * SET-Methode für Account
     * @param account Account
     */
    public void setAccount(Account account) {
        this.account = account;
    }

    /**
     * GET-Methode für Account
     * @return Account
     */
    public Account getAccount() {
        return this.account;
    }

    /**
     * SET-Methode für Schuld
     * @param debt Debt
     */
    public void setDebt(Debt debt) { this.debt = debt; }

    /**
     * GET-Methode für Schuld
     * @return Debt
     */
    public Debt getDebt() {
        return this.debt;
    }

    /**
     * GET-Methode für OnlineIntegrationServiceInterface
     * @return OnlineIntergrationServiceInterface
     */
    public OnlineIntegrationServiceInterface getOnlineIntegrationServiceInterface() {
        return this.onlineIntegrationService;
    }
}
