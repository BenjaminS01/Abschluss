package com.example.Besucher.Controller;


import com.example.Besucher.Client.FirmenverwaltungServiceClient;
import com.example.Besucher.Model.CompanyData;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreaker;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.function.Function;
import java.util.function.Supplier;

@RequiredArgsConstructor
@Controller
public class guestController {

    @Autowired
    FirmenverwaltungServiceClient firmenverwaltungServiceClient;


    private static final String API_URL = "http://localhost:8081";

    //Startseite
    @GetMapping("/")
    public String  home(Model model) {

        return  "start";

    }

    @GetMapping("/vorträge")
    public String presentation(Model model) {

        return  "vorträge";

    }

    // Firmenübersicht
    @GetMapping("/firmen")
    public String allCompanies(Model model) throws JSONException {


        List<CompanyData> companies = new ArrayList<>();


        CircuitBreakerConfig config = CircuitBreakerConfig.custom()
                .slidingWindowType(CircuitBreakerConfig.SlidingWindowType.COUNT_BASED)
                .slidingWindowSize(3)
                .slowCallRateThreshold(50.0f)
                .slowCallDurationThreshold(Duration.ofSeconds(1))
                .build();

        CircuitBreakerRegistry registry = CircuitBreakerRegistry.of(config);
        CircuitBreaker circuitBreaker = registry.circuitBreaker("test");


        Supplier<List<CompanyData>> companyDataSupplier =
                () -> firmenverwaltungServiceClient.allCompanies();
        Supplier<List<CompanyData>> decoratedCompanyDataSupplier =
                circuitBreaker.decorateSupplier(companyDataSupplier);

     //   for (int i = 1; i < 11; i++){     // Test des Circuit Breakers
           try {

                companies = decoratedCompanyDataSupplier.get();
       //         System.out.println(companies.get(0).getCompanyName());

            } catch (CallNotPermittedException e) {

      //          System.out.println(e.getMessage());
                companies.clear();

            }
     //  }
        model.addAttribute("companies", companies);
        return  "firmen";

    }

}
