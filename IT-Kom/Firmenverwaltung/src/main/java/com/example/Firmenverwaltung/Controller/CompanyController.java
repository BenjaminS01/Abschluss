package com.example.Firmenverwaltung.Controller;

import com.example.Firmenverwaltung.Model.CompanyData;
import com.example.Firmenverwaltung.Repository.CompanyDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


// Gibt die Startseite des Firmenverwaltungsbereiches zurück (für Rolle Firma)
@Controller
public class CompanyController {

    @Autowired
    CompanyDataRepository companyDataRepository;

    @GetMapping("/")
    public String start(@AuthenticationPrincipal Jwt jwt, Model model) {


        String id = jwt.getSubject();

        List<CompanyData> companyDataList = companyDataRepository.findBySubject(id);

        CompanyData companyData = new CompanyData();
        companyData.setCompanyName("");
        companyData.setUrl("");

        if(!companyDataList.isEmpty()) {
            companyData = companyDataList.get(0);
        }

        model.addAttribute("company", companyData);


        return "firmenverwaltung";

    }

}