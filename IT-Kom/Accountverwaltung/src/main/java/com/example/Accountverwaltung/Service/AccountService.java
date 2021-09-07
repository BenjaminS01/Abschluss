package com.example.Accountverwaltung.Service;

import com.example.Accountverwaltung.Dto.AccountDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AccountService extends UserDetailsService {
    AccountDto createAccount(AccountDto accountDetails);
    AccountDto getAccountByUsername(String username);
}
