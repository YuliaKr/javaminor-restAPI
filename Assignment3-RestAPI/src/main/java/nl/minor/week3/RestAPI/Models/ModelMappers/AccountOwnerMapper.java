package nl.minor.week3.RestAPI.Models.ModelMappers;

import nl.minor.week3.RestAPI.Models.DomainModels.AccountOwner;
import nl.minor.week3.RestAPI.Models.Dtos.AccountOwnerDto;
import org.springframework.stereotype.Component;

@Component
public class AccountOwnerMapper {
    public AccountOwnerDto ToDto(AccountOwner accountHolder) {
        var newDto = new AccountOwnerDto();
        newDto.setFirstName(accountHolder.getFirstName());
        newDto.setLastName(accountHolder.getSurname());
        return newDto;
    }

    public AccountOwner ToDomainModel(AccountOwnerDto accountHolder) {
        var newDomainModel = new AccountOwner();
        newDomainModel.setFirstName(accountHolder.getFirstName());
        newDomainModel.setSurname(accountHolder.getLastName());
        return newDomainModel;
    }
}
