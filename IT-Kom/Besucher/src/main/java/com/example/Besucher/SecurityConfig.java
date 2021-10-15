package com.example.Besucher;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {



    @Override
    public void configure(HttpSecurity security) throws Exception {
        security.authorizeRequests()
                .antMatchers("/**").permitAll()
            //    .antMatchers("http://localhost:8081/firmenverwaltung/allCompanies").permitAll()
                .antMatchers("http://localhost:8081/firmenservice/allCompanies").permitAll()
                .and()
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
    }

}
