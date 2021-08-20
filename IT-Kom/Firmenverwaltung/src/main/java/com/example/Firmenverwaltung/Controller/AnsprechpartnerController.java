package com.example.Firmenverwaltung.Controller;

import com.example.Firmenverwaltung.Modell.Ansprechpartner;
import com.example.Firmenverwaltung.Repository.AnsprechpartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AnsprechpartnerController {
    @Autowired
    AnsprechpartnerRepository ansprechpartnerRepository;
    @GetMapping("/")
    @ResponseBody
    public String test() {

            return "Keine Person mit dieser Id gefunden :-(";
        }

}
