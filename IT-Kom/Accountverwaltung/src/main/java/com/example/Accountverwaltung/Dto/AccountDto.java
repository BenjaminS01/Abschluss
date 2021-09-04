package com.example.Accountverwaltung.Dto;

import java.io.Serializable;

public class AccountDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String username;
    private String password;
    private String accountId;
    private String encrryptedPasword;


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

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getEncrryptedPasword() {
        return encrryptedPasword;
    }

    public void setEncrryptedPasword(String encrryptedPasword) {
        this.encrryptedPasword = encrryptedPasword;
    }
}
