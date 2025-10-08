package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.RolVistas;
import com.example.demo.service.RolVistaService;

import org.springframework.ui.Model;

@Controller 
@RequestMapping("/rol_vistas")
public class RolVistasController {

	@Autowired
	private RolVistaService rolVistaService;
	
    @GetMapping("/listar")
    public String listarUsuarios(Model model) {
    	List<RolVistas> listaRolVista = rolVistaService.listaRolVistaList();
    	model.addAttribute("rolVistas", listaRolVista);
        return "rol_vistas/listar"; // templates/usuarios/listar.html
    }
}