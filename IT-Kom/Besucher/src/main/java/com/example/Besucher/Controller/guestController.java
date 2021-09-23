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

   // @Autowired
    //@LoadBalanced
    //RestTemplate restTemplate;

    private static final String API_URL = "http://localhost:8081";




    @GetMapping("/")
    public String  home(Model model) {


        return  "start";

    }

    @GetMapping("/firmen")
    public String companies(Model model) throws JSONException {


        List<CompanyData> companieData = new ArrayList<>();



/*
        Resilience4JCircuitBreaker circuitBreaker = circuitBreakerFactory.create("inventory");
        java.util.function.Supplier<List<CompanyData>> booleanSupplier = () -> firmenverwaltungServiceClient.allCompanies().stream().allMatch();

        List<CompanyData> finalCompanieData = companieData;
        companieData = circuitBreaker.run(booleanSupplier, throwable -> handleErrorCase());


 */

        CircuitBreakerRegistry circuitBreakerRegistry
                = CircuitBreakerRegistry.ofDefaults();

        CircuitBreakerConfig config = CircuitBreakerConfig.custom()
                .slidingWindowType(CircuitBreakerConfig.SlidingWindowType.COUNT_BASED)
                .slidingWindowSize(5)
                .slowCallRateThreshold(65.0f)
                .slowCallDurationThreshold(Duration.ofSeconds(1))
                .build();


        CircuitBreakerRegistry registry = CircuitBreakerRegistry.of(config);
        CircuitBreaker circuitBreaker = registry.circuitBreaker("my");


        Supplier<List<CompanyData>> flightsSupplier =
                () -> firmenverwaltungServiceClient.allCompanies();
        Supplier<List<CompanyData>> decoratedFlightsSupplier =
                circuitBreaker.decorateSupplier(flightsSupplier);
        for (int i = 1; i < 11; i++){
            try {
                System.out.println(decoratedFlightsSupplier.get());
                companieData = decoratedFlightsSupplier.get();

                model.addAttribute("companies", companieData);
            } catch (CallNotPermittedException e) {
                System.out.println(e.getMessage());
                CompanyData companyData1 = new CompanyData();
                companyData1.setCompanyName("null..");
                companyData1.setLogoPath("....");
                companyData1.setTakesPart("...");

                companieData.add(companyData1);

                model.addAttribute("companies", companieData);

                return "firmen";
            }
        }

     /*   Supplier<String> decoratedSupplier = CircuitBreaker
                .decorateSupplier(circuitBreaker, test(model));

        String result = Try.ofSupplier(decoratedSupplier)
                .recover(throwable -> "Hello from Recovery").get();


      */

       // companieData = new ArrayList<>();




       /*
        String companiesStr = getCompanies();
        if(companiesStr == null){
            model.addAttribute("companies", companieData);
            return "firmen";
        }
        JSONArray companiesArray = new JSONArray(companiesStr);

        for(int i=0; i < companiesArray.length(); i++)
        {
            JSONObject object = companiesArray.getJSONObject(i);

            CompanyData company = new CompanyData();
            company.setCompanyName(object.getString("companyName"));
            company.setTakesPart(object.getString("takesPart"));
            company.setLogoPath(object.getString("logoPath"));

            companieData.add(company);


        }

        */

   //     companieData = firmenverwaltungServiceClient.allCompanies();

      //  model.addAttribute("companies", companieData);
        return  "firmen";

    }
/*
    private String getCompanies( )
    {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        Cookie[] session = attr.getRequest().getCookies();


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.set("cookie", "SESSION="+session[0].getValue());


      //
        HttpEntity<String> entity = new HttpEntity<String>("access_token",headers);

        final String uri = "http://localhost:8081/firmenverwaltung/allCompanies";

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response = null;

        response = getRestTemplate().exchange(uri, HttpMethod.GET, entity, String.class);

        try {

        }
        catch(Exception e){
            return null;
        }

        return response.getBody().toString();
    }

    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }


 */

    List<CompanyData> test(Model model){

        List<CompanyData> _companieData = firmenverwaltungServiceClient.allCompanies();

        model.addAttribute("companies", _companieData);

        return _companieData;
    }

    private List<CompanyData> handleErrorCase() {
        List<CompanyData> companieData2 = null;
        return companieData2;
    }

}
