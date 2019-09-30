package nl.minor.week3.RestAPI.Models.Dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AccountOwnerDto {
    @NotBlank
    @Size(min=1, max=20)
    private String firstName;

    @NotBlank
    @Size(min=1, max=20)
    private String surname;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String lastName) {
        this.surname = lastName;
    }

    public AccountOwnerDto (String firstName, String surname){
        firstName = firstName;
        surname = surname;
    }
}
