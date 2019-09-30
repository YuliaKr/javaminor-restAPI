package nl.minor.week3.RestAPI.Models.ModelMappers;

import nl.minor.week3.RestAPI.Models.DomainModels.AccountOwner;
import nl.minor.week3.RestAPI.Models.Dtos.AccountOwnerDto;
import org.springframework.stereotype.Component;

@Component
public class AccountOwnerMapper {
    public AccountOwnerDto ToDto(AccountOwner accountOwner) {
        var dto = new AccountOwnerDto(accountOwner.getFirstName(), accountOwner.getSurname());
        return dto;
    }

    public AccountOwner ToDomainModel(AccountOwnerDto accountOwnerDto) {
        var domainModel = new AccountOwner(accountOwnerDto.getFirstName(), accountOwnerDto.getSurname());
        return domainModel;
    }
}
