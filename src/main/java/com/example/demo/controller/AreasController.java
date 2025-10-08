package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.Area;
import com.example.demo.service.AreaService;

@Controller 
@RequestMapping("/areas")
public class AreasController {
	
	@Autowired
	private AreaService areaService;

    @GetMapping("/listar")
    public String listarArea(Model model) {
    	List<Area> listaArea = areaService.listarArea();
    	model.addAttribute("Areas", listaArea);
        return "areas/listar"; // templates/usuarios/listar.html
    }
}