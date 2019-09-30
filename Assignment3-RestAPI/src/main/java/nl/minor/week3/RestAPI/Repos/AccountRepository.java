package nl.minor.week3.RestAPI.Repos;

import nl.minor.week3.RestAPI.Models.DomainModels.Account;
import nl.minor.week3.RestAPI.Models.DomainModels.AccountOwner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class AccountRepository {

    private ArrayList<Account> accounts = new ArrayList<>();

    public Collection<Account> GetAccounts(Integer offset, Integer limit) {
        return accounts.stream().skip(offset).limit(limit).collect(Collectors.toList());
    }

    public Optional<Account> GetAccount(Long accountId){
        return accounts.stream().filter(a -> a.getId() == accountId).findFirst();
    }

    public Collection<Account> GetAccounts(String firstName, String lastName) {
        return accounts.stream()
                .filter(a -> a.getAccountOwners().stream().anyMatch(ao -> ao.getFirstName() == firstName && ao.getSurname() == lastName))
                .collect(Collectors.toList());
    }
    public void Delete(Account account) {
        accounts.removeIf(a -> a.equals(account));
    }


    public void Save(Account account) {
        var existingAccount = accounts.stream().filter(a -> a.getId().equals(account.getId())).findAny().orElse(null);
        if (existingAccount != null){
            accounts.set(accounts.indexOf(existingAccount), account);
        } else {
            accounts.add(account);
        }
    }

    public void BlockAccount(Long accountId){
        var optionalAccount = GetAccount(accountId);
        if (optionalAccount.isPresent()){
            var account = optionalAccount.get();
            account.setIsBlocked(true);
            Save(account);
        }
    }

    public void AddAccountOwner(Long accountId, AccountOwner accountOwner){
        var optionalAccount = GetAccount(accountId);
        if (optionalAccount.isPresent()){
            var account = optionalAccount.get();
            account.getAccountOwners().add(accountOwner);
            Save(account);
        }
    }

    public void RemoveAccountOwner(Long accountId, AccountOwner accountOwner){
        var optionalAccount = GetAccount(accountId);
        if (optionalAccount.isPresent()){
            var account = optionalAccount.get();
            account.getAccountOwners().removeIf(ao -> ao.getFirstName() == accountOwner.getFirstName()  && ao.getSurname() == accountOwner.getSurname());
            Save(account);
        }
    }
}
