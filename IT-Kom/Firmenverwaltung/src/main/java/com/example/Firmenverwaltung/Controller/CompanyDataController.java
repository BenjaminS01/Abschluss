package com.example.Firmenverwaltung.Controller;

import com.example.Firmenverwaltung.Model.CompanyData;
import com.example.Firmenverwaltung.Repository.CompanyDataRepository;
import com.nimbusds.jose.shaded.json.JSONArray;
import com.nimbusds.jose.shaded.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;


@RestController
public class CompanyDataController {

    @Autowired
    CompanyDataRepository companyDataRepository;


    @PreAuthorize("hasAuthority('SCOPE_TEST')")
    @GetMapping("/create")
    @ResponseBody
    public String create(@AuthenticationPrincipal Jwt jwt) {

       Map x = jwt.getClaims();
        JSONObject z  = (JSONObject) x.get("realm_access");
        JSONArray lll = (JSONArray) z.get("roles");



        int i =1;
     // Collection<String> z = x.values();


      if(!lll.contains("Firma")){
          return "keine Berechtigung";
      }


     //  JSONObject y = (JSONObject) x.get("realm_acces");
        String id = jwt.getSubject();

        CompanyData companyData = new CompanyData();
        companyData.setCompanyName("Firma1");
        companyData.setLogoPath("/pfad/");
        companyData.setTakesPart(false);
        companyData.setSubject(id);
        companyDataRepository.save(companyData);

        return "company created";

    }

    @GetMapping("/company")
    @ResponseBody
    public ResponseEntity  <List<CompanyData>> company(@AuthenticationPrincipal Jwt jwt) {

        String id = jwt.getSubject();

        List<CompanyData> companyData = companyDataRepository.findBySubject(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(companyData);

    }

    @GetMapping("/allCompanies")
    @ResponseBody
    public ResponseEntity <List<CompanyData>> allCompanies() throws InterruptedException {

        Thread.sleep(4000);

        List<CompanyData> companyDataList = companyDataRepository.findAll();
        return ResponseEntity.status(HttpStatus.CREATED).body(companyDataList);

    }
}



