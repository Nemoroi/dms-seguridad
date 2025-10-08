package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.Vistas;
import com.example.demo.service.VistaService;

import org.springframework.ui.Model;

@Controller 
@RequestMapping("/vistas")
public class VistasController {
	
	@Autowired
	private VistaService vistaService;

    @GetMapping("/listar")
    public String listarUsuarios(Model model) {
    	List<Vistas> listaVistas = vistaService.listaVistaList();
    	model.addAttribute("vistas", listaVistas);
        return "vistas/listar"; // templates/usuarios/listar.html
    }
}