package com.indocyber.DTO.Account;


import javax.validation.constraints.NotNull;

public class AccountDto {

    @NotNull(message = "This field is required")
    private String username;

    @NotNull(message = "This field is required")
    private String password;

    public AccountDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

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
}
