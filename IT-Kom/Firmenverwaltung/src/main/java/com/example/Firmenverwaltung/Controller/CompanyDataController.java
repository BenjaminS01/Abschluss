package com.example.Firmenverwaltung.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/firmendaten")
public class CompanyDataController {
    @GetMapping("/edit")
    @ResponseBody
    public String edit() {

        return "edit";

    }

    @GetMapping("/show")
    @ResponseBody
    public String show() {

        return "show";

    }
}
