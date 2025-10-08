package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ch.qos.logback.core.model.Model;

@Controller 
@RequestMapping("/usuarios")
public class UsuarioController {

    @GetMapping("/listar")
    public String listarUsuarios(Model model) {
        return "usuarios/listar"; // templates/usuarios/listar.html
    }
}