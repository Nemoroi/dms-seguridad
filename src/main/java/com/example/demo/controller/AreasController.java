package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.Area;
import com.example.demo.service.AreaService;
import com.example.demo.service.UsuarioService;

@Controller 
@RequestMapping("/areas")
public class AreasController {
	
	@Autowired
	private AreaService areaService;
	
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/listar")
    public String listarArea(Model model) {
    	List<Area> listaArea = areaService.listarArea();
    	model.addAttribute("Areas", listaArea);
        return "areas/listar"; // templates/usuarios/listar.html
    }
    
    // 👇 NUEVOS MÉTODOS PARA CREAR

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevaArea(Model model) {
        model.addAttribute("area", new Area());
  //      model.addAttribute("jefes", usuarioService.listarUsuariosList()); // lista desplegable
        return "areas/crear";
    }

    @PostMapping("/guardar")
    public String guardarArea(@ModelAttribute("area") Area area) {
        areaService.guardarArea(area);
        return "redirect:/areas/listar";
    }
    
    // 🔹 Eliminar (o desactivar) área por ID
    @GetMapping("/eliminar/{id}")
    public String eliminarArea(@PathVariable("id") Integer id, RedirectAttributes redirectAttrs) {
        areaService.eliminarArea(id);
        redirectAttrs.addFlashAttribute("mensaje", "Área eliminada correctamente ✅");
        return "redirect:/areas/listar"; // vuelve a la lista de áreas
    }
}