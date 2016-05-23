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
    private String email;
    private String password;
    private HashMap<Integer,Debt> debts;
    private HashMap<Integer,Claim> claims;
    private ArrayList<Account> friendlist;

    public Account (String userName, String email, String password){
        this.id = lastID++;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.debts = new HashMap<>();
        this.claims = new HashMap<>();
        this.friendlist = new ArrayList<>();
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

    public String getEmail(){
        return this.email;
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

    public void addFriendlist(Account newAccount){
        friendlist.add(newAccount);
    }

    public ArrayList<Account> getFriendlist(){
        return this.friendlist;
    }

    public String getFriendname(){
        for(int i = 0; i<friendlist.size();i++){
            Account x = friendlist.get(i);
            String y = x.getUserName();
            return y;
        }
        return null;
    }

    public String toString() {
        return "Account (" + this.getId() + "): " + this.getUserName();
    }
}

