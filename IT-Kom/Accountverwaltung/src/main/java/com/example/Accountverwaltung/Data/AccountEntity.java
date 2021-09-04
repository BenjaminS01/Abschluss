package com.example.Accountverwaltung.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="account")
public class AccountEntity implements Serializable {
    private static final long serialVersionUID = 2L;

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String encrryptedPasword;

    @Column(nullable = false)
    private String accountId;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
