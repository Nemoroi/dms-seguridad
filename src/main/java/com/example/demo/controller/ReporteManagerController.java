package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.Reporte;
import com.example.demo.service.ReporteManagerService;


@Controller 
@RequestMapping("/reportesmanager")
public class ReporteManagerController {
	@Autowired
	private ReporteManagerService reporteManagerService;

    @GetMapping("/listar")
    public String listarReporteManager(Model model) {
    	List<Reporte> listaReporteManager = reporteManagerService.listarreporte();
    	model.addAttribute("reportesmanager", listaReporteManager);
        return "reportesmanager/listar"; // templates/usuarios/listar.html
    }
}