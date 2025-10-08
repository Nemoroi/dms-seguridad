package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ch.qos.logback.core.model.Model;

@Controller 
@RequestMapping("/vistas")
public class VistasController {

    @GetMapping("/listar")
    public String listarUsuarios(Model model) {
        return "vistas/listar"; // templates/usuarios/listar.html
    }
}