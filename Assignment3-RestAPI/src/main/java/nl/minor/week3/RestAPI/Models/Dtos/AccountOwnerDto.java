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

    public String getLastName() {
        return surname;
    }

    public void setLastName(String lastName) {
        this.surname = lastName;
    }
}
