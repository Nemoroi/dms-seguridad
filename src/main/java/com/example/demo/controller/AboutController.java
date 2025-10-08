package com.example.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("about")
public class AboutController {

    @GetMapping("/about")
    public String about() {
        // Retorna el nombre de la plantilla (about.html)
        return "about";
    }
}