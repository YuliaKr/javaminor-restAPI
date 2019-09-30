package nl.minor.week3.RestAPI.Models.Dtos;

//import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AccountOwnerDto {
    @NotBlank
    @Size(min=1, max=20)
    //@ApiModelProperty(notes = "The first name of the account holder", required = true, position = 0)
    private String firstName;

    @NotBlank
    @Size(min=1, max=20)
    //@ApiModelProperty(notes = "The last name of the account holder", required = true, position = 1)
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
