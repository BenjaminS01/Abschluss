package com.example.Firmenverwaltung.Controller;

import com.example.Firmenverwaltung.Repository.ContactPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/ansprechpartner")
public class ContactPersonController {

    @Autowired
    ContactPersonRepository contactPersonRepository;

    @GetMapping("/test/check")
    @ResponseBody
    public String test() {

            return "Keine Person mit dieser Id gefunden :-(";

        }

}
