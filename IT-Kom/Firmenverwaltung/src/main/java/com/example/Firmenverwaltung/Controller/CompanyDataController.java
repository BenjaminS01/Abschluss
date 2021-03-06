package com.example.Firmenverwaltung.Controller;

import com.example.Firmenverwaltung.Model.CompanyData;
import com.example.Firmenverwaltung.Repository.CompanyDataRepository;
import com.nimbusds.jose.shaded.json.JSONArray;
import com.nimbusds.jose.shaded.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.EurekaInstanceConfigBean;
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


       //erhalte die Rollen des Nutzers
       Map claims = jwt.getClaims();
       JSONObject realm_access  = (JSONObject) claims.get("realm_access");
       JSONArray roles = (JSONArray) realm_access.get("roles");

        //Prüft Autorisierung
        if(!roles.contains("Firma")){
            return "keine Berechtigung";
        }

        //Anlegen von Testdatensätzen

        // erhalte NutzerId
        String id = jwt.getSubject();

        CompanyData companyData = new CompanyData();
        companyData.setCompanyName("Firma 1");
        companyData.setLogoPath("/pfad/");
        companyData.setTakesPart(false);
        companyData.setUrl("www.firma_1.de");
        companyData.setSubject(id);
        companyDataRepository.save(companyData);

        CompanyData companyData2 = new CompanyData();
        companyData2.setCompanyName("Firma 2");
        companyData2.setLogoPath("/pfad/");
        companyData2.setTakesPart(false);
        companyData2.setUrl("www.firma_2.de");
        companyData2.setSubject("2");
        companyDataRepository.save(companyData2);

        CompanyData companyData3 = new CompanyData();
        companyData3.setCompanyName("Firma 3");
        companyData3.setLogoPath("/pfad/");
        companyData3.setTakesPart(false);
        companyData3.setUrl("www.firma_3.de");
        companyData3.setSubject("3");
        companyDataRepository.save(companyData3);

        return "company created";

    }

    // Gibt die Firmendaten zu dem entsprechenden Firmenaccount zurück
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

       // Thread.sleep(1100);    // Test des Circuit Breakers

        List<CompanyData> companyDataList = companyDataRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(companyDataList);

    }
}



