package com.example.group7.debtcheckapp.Mock;

/**
 * Created by niklasschluter on 17.05.16.
 */

        import java.io.Serializable;
        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.List;



public class Account implements Serializable {

    private static final long serialVersionUID = 1L;
    private static int lastID = 0;

    private int id;
    private String userName;
    private String password;
    private HashMap<Integer,Debt> debts;
    private HashMap<Integer,Claim> claims;

    public Account (String userName, String password){
        this.id = lastID++;
        this.userName = userName;
        this.password = password;
        this.debts = new HashMap<>();
        this.claims = new HashMap<>();
    }

    public void addNewDebt(Debt newDebt) {
        this.debts.put(newDebt.getId(), newDebt);
    }

    public void addNewClaim(Claim newClaim) {
        this.claims.put(newClaim.getId(), newClaim);
    }

    public String getUserName() {
        return this.userName;
    }

    public String getPassword() {
        return this.password;
    }

    public Debt getDebtById(int debtId) {
        return debts.get(debtId);
    }

    public List<Debt> getDebts() {
        return new ArrayList<Debt>(debts.values());
    }

    public Claim getClaimById(int ClaimId) {
        return claims.get(ClaimId);
    }

    public List<Claim> getClaim() {
        return new ArrayList<Claim>(claims.values());
    }

    public int getId() {
        return id;
    }

    public void setId(int accountId) {
        this.id = accountId;
    }

    public String toString() {
        return "Account (" + this.getId() + "): " + this.getUserName();
    }
}

