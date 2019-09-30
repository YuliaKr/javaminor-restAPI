package nl.minor.week3.RestAPI.Models.DomainModels;

public class AccountOwner {
    private String FirstName;
    private String Surname;

    //getters and setters
    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        this.FirstName = firstName;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        this.Surname = surname;
    }

    public AccountOwner (String firstName, String surname) {
        FirstName = firstName;
        Surname = surname;
    }
}
