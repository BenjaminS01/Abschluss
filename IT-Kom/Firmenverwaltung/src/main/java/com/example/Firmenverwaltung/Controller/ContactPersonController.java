package com.example.Firmenverwaltung.Controller;

import com.example.Firmenverwaltung.Repository.ContactPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class ContactPersonController {
/*
    @Autowired
    ContactPersonRepository contactPersonRepository;
*/
    @GetMapping("/test/check")
    public String blubb() {

        return "alleFirmen";

    }

    @GetMapping("/alleFirmen")
    public String blubbb() {

        return "alleFirmen";

    }

}


