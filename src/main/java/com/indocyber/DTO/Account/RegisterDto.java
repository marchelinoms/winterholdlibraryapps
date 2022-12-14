package com.indocyber.DTO.Account;

import javax.validation.constraints.NotNull;

public class RegisterDto {

    @NotNull(message = "This field is required")
    private String username;

    @NotNull(message = "This field is required")
    private String password;

    @NotNull(message = "This field is required")
    private String passwordConfirmation;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
    }
}
