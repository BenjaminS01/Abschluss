package com.example.Vortragsverwaltung;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vortrag")
public class PresentationController {

    @GetMapping("/abc")
    @ResponseBody
    public String test() {

        return "test123";

    }

    @GetMapping("/neuerVortrag")
    @ResponseBody
    public String neuerVorträg() {

        return "alleVorrträge";

    }

    @GetMapping("/alleVorträge")
    @ResponseBody
    public String alleVorträge() {

        return "alleVorrträge";

    }
}
