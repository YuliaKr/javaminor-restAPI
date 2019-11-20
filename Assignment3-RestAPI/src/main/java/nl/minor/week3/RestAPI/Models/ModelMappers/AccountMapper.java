package nl.minor.week3.RestAPI.Models.ModelMappers;

import nl.minor.week3.RestAPI.Models.DomainModels.Account;
import nl.minor.week3.RestAPI.Models.Dtos.AccountDto;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class AccountMapper {
    private final AccountOwnerMapper _accountOwnerMapper;

    public AccountMapper(AccountOwnerMapper accountOwnerMapper){
        _accountOwnerMapper = accountOwnerMapper;
    }

    public AccountDto ToDto(Account account) {
        var dto = new AccountDto();
        dto.setId(account.getId());

        dto.setBalance(account.getBalance());
        dto.setIban(account.getIban());
        dto.setIsBlocked(account.getIsBlocked());

        if(account.getAccountOwners() != null){
            dto.setAccountHolders(account.getAccountOwners().stream().map(_accountOwnerMapper::ToDto).collect(Collectors.toList()));
        }
        return dto;
    }

    public Account ToDomainModel(AccountDto account) {
        var domainModel = new Account();
        domainModel.setId(account.getId());
        domainModel.setBalance(account.getBalance());
        domainModel.setIban(account.getIban());
        domainModel.setIsBlocked(account.getIsBlocked());

        if (account.getAccountHolders() != null){
            domainModel.setAccountOwners(account.getAccountHolders().stream().map(_accountOwnerMapper::ToDomainModel).collect(Collectors.toList()));
        }
        return domainModel;
    }
}

