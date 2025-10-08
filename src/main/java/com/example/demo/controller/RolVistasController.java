package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ch.qos.logback.core.model.Model;

@Controller 
@RequestMapping("/rol_vistas")
public class RolVistasController {

    @GetMapping("/listar")
    public String listarUsuarios(Model model) {
        return "rol_vistas/listar"; // templates/usuarios/listar.html
    }
}