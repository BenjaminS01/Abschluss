package com.example.Accountverwaltung.security;

import com.example.Accountverwaltung.Dto.AccountDto;
import com.example.Accountverwaltung.Model.LoginRequestModel;
import com.example.Accountverwaltung.Service.AccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AccountService accountService;
    private Environment environment;

    public AuthenticationFilter(AccountService accountService, Environment environment, AuthenticationManager authenticationManager){
        this.accountService = accountService;
        this.environment = environment;
        super.setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        try{
            LoginRequestModel loginRequestModel = new ObjectMapper().readValue(request.getInputStream(), LoginRequestModel.class);

            return getAuthenticationManager().authenticate( new UsernamePasswordAuthenticationToken(
                    loginRequestModel.getUsername(),
                    loginRequestModel.getPassword(),
                    new ArrayList<>())
            );
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        String username = ((User) authResult.getPrincipal()).getUsername();
        AccountDto accountDetails = accountService.getAccountByUsername(username);

        String token = Jwts.builder()
                .setSubject(accountDetails.getAccountId())
                .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(environment.getProperty("token.expiration_time"))))
                .signWith(SignatureAlgorithm.HS384, environment.getProperty("token.secret"))
                .compact();

        response.addHeader("token", token);
        response.addHeader("userId", accountDetails.getAccountId());

    }


}
