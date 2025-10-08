package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.Usuario;
import com.example.demo.service.UsuarioService;

import org.springframework.ui.Model;

@Controller 
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;

    @GetMapping("/listar")
    public String listarUsuarios(Model model) {
        List<Usuario> listaUsuarios = usuarioService.listarUsuariosList();
        model.addAttribute("usuarios", listaUsuarios);
        return "usuarios/listar"; // templates/usuarios/listar.html
    }
}