package com.example.ApiGateway;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http.authorizeExchange()
                .pathMatchers("/besucherservice/**").permitAll()
                .pathMatchers("/firmenservice/").authenticated()
                .pathMatchers("/firmenservice/create").authenticated()
                .pathMatchers("/firmenservice/allCompanies").permitAll()

                .and()
                .oauth2Login(withDefaults())
                .oauth2Client();
        http.csrf().disable();
        return http.build();
    }
}