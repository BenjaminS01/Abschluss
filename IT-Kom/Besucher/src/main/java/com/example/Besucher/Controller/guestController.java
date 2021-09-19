package com.example.Besucher.Controller;


import com.example.Besucher.Model.CompanieData;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import java.util.ArrayList;
import java.util.List;


@Controller
public class guestController {

    @Autowired
    @LoadBalanced
    RestTemplate restTemplate;

    private static final String API_URL = "http://localhost:8081";




    @GetMapping("/")
    public String  home(Model model) {


        return  "start";

    }

    @GetMapping("/firmen")
    public String companies(Model model) throws JSONException {

       List<CompanieData> companieData = new ArrayList<>();

        String companiesStr = getCompanies();
        if(companiesStr == null){
            model.addAttribute("companies", companieData);
            return "firmen";
        }
        JSONArray companiesArray = new JSONArray(companiesStr);

        for(int i=0; i < companiesArray.length(); i++)
        {
            JSONObject object = companiesArray.getJSONObject(i);

            CompanieData company = new CompanieData();
            company.setCompanyName(object.getString("companyName"));
            company.setTakesPart(object.getString("takesPart"));
            company.setLogoPath(object.getString("logoPath"));

            companieData.add(company);


        }
        model.addAttribute("companies", companieData);
        return  "firmen";

    }

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
}
