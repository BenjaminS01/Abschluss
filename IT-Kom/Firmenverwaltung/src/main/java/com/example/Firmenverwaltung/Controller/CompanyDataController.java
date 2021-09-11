package com.example.Firmenverwaltung.Controller;

import com.example.Firmenverwaltung.Model.CompanyData;
import com.example.Firmenverwaltung.Repository.CompanyDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
public class CompanyDataController {

    @Autowired
    CompanyDataRepository companyDataRepository;


    @GetMapping("/create")
    @ResponseBody
    public String create(@AuthenticationPrincipal Jwt jwt) {

        String id = jwt.getSubject();

        CompanyData companyData = new CompanyData();
        companyData.setCompanyName("Firma1");
        companyData.setLogoPath("/pfad/");
        companyData.setTakesPart(false);
        companyData.setSubject(id);
        companyDataRepository.save(companyData);

        return "edit";

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
    public ResponseEntity <List<CompanyData>> allCompanies() {

        List<CompanyData> companyDataList = companyDataRepository.findAll();
        return ResponseEntity.status(HttpStatus.CREATED).body(companyDataList);

    }
}



