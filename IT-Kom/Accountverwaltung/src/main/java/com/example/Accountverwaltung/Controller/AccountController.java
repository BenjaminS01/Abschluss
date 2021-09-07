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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    Environment env;

    @Autowired
    AccountService accountService;

    @PostMapping("/register")
    public String createAccont(@Valid  CreateAccountRequestModel accountDetails, Model model){

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        AccountDto accountDto = modelMapper.map(accountDetails, AccountDto.class);
        AccountDto createdAccount = accountService.createAccount(accountDto);

        CreateAccountResponseModel returnValue = modelMapper.map(createdAccount, CreateAccountResponseModel.class);

        return "redirect:/account/register";
    }

    @GetMapping("/register")
    public String register( Model model) {

        model.addAttribute("title", "register");

        model.addAttribute("accountDetails", new CreateAccountRequestModel());

        return "register";

    }

}
