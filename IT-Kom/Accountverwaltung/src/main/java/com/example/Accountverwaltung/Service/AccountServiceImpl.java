package com.example.Accountverwaltung.Service;

import com.example.Accountverwaltung.Data.AccountEntity;
import com.example.Accountverwaltung.Data.AccountRepository;
import com.example.Accountverwaltung.Dto.AccountDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
import org.springframework.stereotype.Service;

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
}
