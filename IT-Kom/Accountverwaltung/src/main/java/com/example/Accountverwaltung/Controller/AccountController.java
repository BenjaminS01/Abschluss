package com.example.Accountverwaltung.Controller;

import com.example.Accountverwaltung.Dto.AccountDto;
import com.example.Accountverwaltung.Model.CreateAccountRequestModel;
import com.example.Accountverwaltung.Model.CreateAccountResponseModel;
import com.example.Accountverwaltung.Service.AccountService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    Environment env;

    @Autowired
    AccountService accountService;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<CreateAccountResponseModel> createAccont(@RequestBody  CreateAccountRequestModel accountDetails, Model model){

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        AccountDto accountDto = modelMapper.map(accountDetails, AccountDto.class);
        AccountDto createdAccount = accountService.createAccount(accountDto);

        CreateAccountResponseModel returnValue = modelMapper.map(createdAccount, CreateAccountResponseModel.class);

        return /*"redirect:/account/register"*/ ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
    }

    @GetMapping("/register")
    public String register( Model model) {

        model.addAttribute("title", "register");

        model.addAttribute("accountDetails", new CreateAccountRequestModel());

        return "register";

    }

}
