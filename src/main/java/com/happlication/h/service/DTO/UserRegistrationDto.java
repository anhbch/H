package com.happlication.h.service.DTO;


import com.happlication.h.security.PasswordMatches;
import com.sun.istack.NotNull;
import org.springframework.validation.annotation.Validated;

@PasswordMatches(message = "password and matching password must match")
@Validated
public class UserRegistrationDto {

    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String userName;

//    @NotNull
//    @ValidEmail
//    private String email;

    @NotNull
    private String password;
    @NotNull
    private String matchingPassword;

    public UserRegistrationDto(String firstName, String lastName, String userName, String password, String matchingPassword) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
//        this.email = email;
        this.password = password;
        this.matchingPassword = matchingPassword;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }

    public UserRegistrationDto() {
    }



}
