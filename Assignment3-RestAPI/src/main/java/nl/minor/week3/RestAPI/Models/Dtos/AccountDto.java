package nl.minor.week3.RestAPI.Models.Dtos;

//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Collection;

public class AccountDto {
    @NotNull
    private Long id;

    //ToDo: add IBAN validation
    @NotNull
    private String iban;

    @NotNull
    private int balance;

    private Boolean isBlocked;

    private Collection<AccountOwnerDto> accountOwners;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public Collection<AccountOwnerDto> getAccountHolders() {
        return accountOwners;
    }

    public void setAccountHolders(Collection<AccountOwnerDto> accountHolders) {
        this.accountOwners = accountHolders;
    }

    public Boolean getIsBlocked() {
        return isBlocked;
    }

    public void setIsBlocked(Boolean blocked) {
        isBlocked = blocked;
    }
}

