package com.example.Besucher;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@Order(1)
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        //  registry.addViewController("contactPerson/test/check").setViewName("/alleFirmen");
        //   registry.addViewController("/alleFirmen").setViewName("/alleFirmen");
    }
}
