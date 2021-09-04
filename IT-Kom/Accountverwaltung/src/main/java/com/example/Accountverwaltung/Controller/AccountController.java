package com.example.Accountverwaltung.Controller;

import com.example.Accountverwaltung.Dto.AccountDto;
import com.example.Accountverwaltung.Model.CreateAccountRequestModel;
import com.example.Accountverwaltung.Service.AccountService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    AccountService accountService;

    @PostMapping
    public String createAccont(@Valid @RequestBody CreateAccountRequestModel accountDeatails){

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        AccountDto accountDto = modelMapper.map(accountDeatails, AccountDto.class);

        return "test";
    }

    @GetMapping("/show")
    @ResponseBody
    public String show() {

        return "show";

    }

}
