package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.Rol;
import com.example.demo.service.RolService;

import org.springframework.ui.Model;

@Controller 
@RequestMapping("/roles")
public class RolesController {
	
	@Autowired
	private RolService rolService;

    @GetMapping("/listar")
    public String listarRoles(Model model) {
    	List<Rol> listaRol = rolService.listaRoles();
    	model.addAttribute("roles", listaRol);
        return "roles/listar"; // templates/usuarios/listar.html
    }
}