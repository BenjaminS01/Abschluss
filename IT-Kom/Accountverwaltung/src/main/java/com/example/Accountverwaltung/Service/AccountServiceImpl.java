package com.example.Accountverwaltung.Service;

import com.example.Accountverwaltung.Data.AccountEntity;
import com.example.Accountverwaltung.Data.AccountRepository;
import com.example.Accountverwaltung.Dto.AccountDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
public class AccountServiceImpl implements AccountService{

    AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDetails) {
        accountDetails.setAccountId(UUID.randomUUID().toString());

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        AccountEntity accountEntity = modelMapper.map(accountDetails, AccountEntity.class);
        accountEntity.setEncrryptedPasword("test");

        accountRepository.save(accountEntity);

        AccountDto returnValue = modelMapper.map(accountEntity, AccountDto.class);

        return returnValue;
    }

    @Override
    public AccountDto getAccountByUsername(String username) {
        AccountEntity accountEntity = accountRepository.findByUsername(username);

        if(accountEntity == null) throw new UsernameNotFoundException(username);

        return new ModelMapper().map(accountEntity, AccountDto.class);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       AccountEntity accountEntity = accountRepository.findByUsername(username);

        if(accountEntity == null) throw new UsernameNotFoundException(username);

        return new User(accountEntity.getUsername(), accountEntity.getEncrryptedPasword(),true, true, true, true, new ArrayList<>());
    }


}
