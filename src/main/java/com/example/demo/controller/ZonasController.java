package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.model.Zona;
import com.example.demo.service.ZonaService;


@Controller 
@RequestMapping("/zonas")
public class ZonasController {	
	@Autowired
	private ZonaService zonaService;

    @GetMapping("/listar")
    public String listarZona(Model model) {
    	List<Zona> listaZona = zonaService.listarZona();
    	model.addAttribute("zonas", listaZona);
        return "zonas/listar"; // templates/usuarios/listar.html
    }
    
    @GetMapping("/porArea/{areaId}")
    @ResponseBody
    public List<Zona> listarPorArea(@PathVariable Integer areaId) {
        return zonaService.listarPorArea(areaId);
        
    }
    
}