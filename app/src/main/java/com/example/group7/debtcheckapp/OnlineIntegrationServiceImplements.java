package com.example.group7.debtcheckapp;

/**
 * Klasse für die Methoden des OnlineIntegrationService
 */


import com.example.group7.Wsdl2Code.OnlineIntegrationService.OnlineIntegrationService;
import com.example.group7.Wsdl2Code.OnlineIntegrationService.addNewDebtResponsee;
import com.example.group7.Wsdl2Code.OnlineIntegrationService.debtListResponse;
import com.example.group7.Wsdl2Code.OnlineIntegrationService.userLoginResponse;
import com.example.group7.debtcheckapp.Exceptions.InvalidGetAllClaimsException;
import com.example.group7.debtcheckapp.Exceptions.InvalidGetAllDebtsException;
import com.example.group7.debtcheckapp.Exceptions.InvalidLogoutException;
import com.example.group7.debtcheckapp.Exceptions.InvalidPayDebtException;
import com.example.group7.debtcheckapp.Exceptions.InvalidSignupException;
import com.example.group7.debtcheckapp.Mock.Account;
import com.example.group7.debtcheckapp.Mock.Debt;
import com.example.group7.debtcheckapp.Exceptions.InvalidLoginException;
import com.example.group7.debtcheckapp.Exceptions.InvalidAddNewDebtException;
import com.example.group7.debtcheckapp.Mock.OnlineIntegrationServiceInterface;
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * @author Niklas Schlüter, André Käthner
 * @version 1.0
 */
public class OnlineIntegrationServiceImplements implements OnlineIntegrationServiceInterface {
    private OnlineIntegrationService webService;
    private int sessionId;

    /**
     * Konstruktor
     */
    public OnlineIntegrationServiceImplements() {
        this.webService = new OnlineIntegrationService();
    }

    /**
     * Methode für das Login
     * @param email String
     * @param password String
     * @return Account
     * @throws InvalidLoginException
     */
    @Override
    public Account login(String email, String password) throws InvalidLoginException {
        userLoginResponse response = this.webService.login(email, password);
        if (response.returnCodeField != 0)
            throw new InvalidLoginException("Login not successful");
        this.sessionId = response.sessionId;
        return new Account(response.account.userName, response.account.password ,response.account.email);
    }

    /**
     * Methode für das Logout
     * @throws InvalidLogoutException
     */
    @Override
    public void logout() throws InvalidLogoutException {
        webService.logout(this.sessionId);
    }

    /**
     * Methode für das Registrieren
     * @param username String
     * @param email String
     * @param password String
     * @return Account
     * @throws InvalidSignupException
     */
    @Override
    public Account signup(String username, String email, String password) throws InvalidSignupException {
        userLoginResponse response = this.webService.registerNewAccount(username, email, password);
        this.sessionId = response.sessionId;
        return new Account(response.account.userName, response.account.email ,response.account.password);
    }

    /**
     * Methode für das erstellen einer neuen Schuld
     * @param username String
     * @param amount BigDecimal
     * @param reason String
     * @return Debt
     * @throws InvalidAddNewDebtException
     */
    @Override
    public Debt addNewDebt(String username, BigDecimal amount, String reason) throws InvalidAddNewDebtException {
        addNewDebtResponsee response = this.webService.addNewDebt(this.sessionId, username, amount.doubleValue(), reason);
        if (response.returnCodeField != 0)
            throw new InvalidAddNewDebtException("Add a new Debt not succesful");
        return new Debt(response.debt.debtor, response.debt.creditor, response.debt.amount, response.debt.reason);
    }

    /**
     * GET-Methode für alle Schulden
     * @return ArrayList
     * @throws InvalidGetAllDebtsException
     */
    @Override
    public ArrayList<Debt> getAllDebts() throws InvalidGetAllDebtsException {
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

    /**
     * GET-Methode für alle Forderungen
     * @return ArrayList
     * @throws InvalidGetAllClaimsException
     */
    @Override
    public ArrayList<Debt> getAllClaims() throws InvalidGetAllClaimsException {
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

    /**
     * Methode für das zurückzahlen von Schulden
     * @param creditor String
     * @param amount BigDecimal
     * @param id int
     * @throws InvalidPayDebtException
     */
    @Override
    public void payDebt(String creditor, BigDecimal amount, int id) throws InvalidPayDebtException {
        this.webService.payDebt(this.sessionId, creditor, amount.doubleValue(), id);
    }
}
