package com.hillel.bankserviceboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/index")
public class MainPageController {

    @GetMapping
    public String getIndexPage() {
        return "index";
    }
}
