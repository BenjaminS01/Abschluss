package com.example.Besucher.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class guestController {

    @GetMapping("/")
    public String blubb() {

        return "start";

    }
}
