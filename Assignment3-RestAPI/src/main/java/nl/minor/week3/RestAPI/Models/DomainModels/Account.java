package nl.minor.week3.RestAPI.Models.DomainModels;

import java.util.Collection;

public class Account {
    private Long ID;
    private String IBAN;
    private int Balance;
    private Boolean IsBlocked;
    private Collection<AccountOwner> AccountOwners;

    //getters and setters
    public Long getId() {
        return ID;
    }

    public void setId(Long id) {
        this.ID = id;
    }

    public String getIban() {
        return IBAN;
    }

    public void setIban(String iban) {
        this.IBAN = iban;
    }

    public int getBalance() {
        return Balance;
    }

    public void setBalance(int balance) {
        this.Balance = balance;
    }

    public Collection<AccountOwner> getAccountOwners() {
        return AccountOwners;
    }

    public void setAccountOwners(Collection<AccountOwner> accountOwners) {
        this.AccountOwners = accountOwners;
    }

    public Boolean getIsBlocked() {
        return IsBlocked;
    }

    public void setIsBlocked(Boolean blocked) {
        IsBlocked = blocked;
    }
}

