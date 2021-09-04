package com.example.Accountverwaltung.Model;

import reactor.util.annotation.NonNull;

import javax.validation.constraints.NotNull;

public class CreateAccountRequestModel {

    @NotNull (message="username null")
    private String username;

    @NotNull (message="password null")
    private String password;

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
