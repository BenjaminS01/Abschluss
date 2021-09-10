package com.example.Firmenverwaltung.Controller;

import com.example.Firmenverwaltung.Model.CompanyData;
import com.example.Firmenverwaltung.Repository.CompanyDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class CompanyDataController {

    @Autowired
    CompanyDataRepository companyDataRepository;

    @GetMapping("/create")
    @ResponseBody
    public String create() {

        CompanyData companyData = new CompanyData();
        companyData.setCommpanyName("name1");
        companyData.setLogoPath("//jkjj/");
        companyData.setTakesPart(false);
        companyDataRepository.save(companyData);

        return "edit";

    }

    @GetMapping("/allCompanies")
    @ResponseBody
    public ResponseEntity <List<CompanyData>> allCompanies() {

        List<CompanyData> companyDataList = companyDataRepository.findAll();
        return ResponseEntity.status(HttpStatus.CREATED).body(companyDataList);

    }
}



