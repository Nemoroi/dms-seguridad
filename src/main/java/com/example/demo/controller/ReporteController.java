package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.Reporte;
import com.example.demo.model.Usuario;
import com.example.demo.service.AreaService;
import com.example.demo.service.ReporteService;
import com.example.demo.service.UsuarioService;
import com.example.demo.service.ZonaService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/reportes")
public class ReporteController {

    @Autowired
    private ReporteService reporteService;

    @Autowired
    private AreaService areaService;

    @Autowired
    private ZonaService zonaService;

    @Autowired
    private UsuarioService usuarioService;
    
    @GetMapping("/listar")
    public String listarReporteManager(Model model, HttpSession session) {

        // ✅ Obtener usuario desde sesión
        Usuario usuarioLogueado = (Usuario) session.getAttribute("usuarioLogueado");

        if (usuarioLogueado == null) {
            // Si no hay sesión activa, redirigir al login
            return "redirect:/login";
        }

        // ✅ Obtener el ID del usuario logueado
        Integer usuarioID = usuarioLogueado.getUsuarioID();

        // ✅ Obtener los reportes del usuario desde el servicio
        List<Reporte> listaReporte = reporteService.listarPorUsuario(usuarioID);

        // ✅ Pasar los reportes al modelo para mostrarlos en la vista
        model.addAttribute("reportes", listaReporte);

        // ✅ Retornar la vista (por ejemplo, templates/reportes/listar.html)
        return "reportes/listar";
    }
    
    // 🔹 Mostrar formulario nuevo
    @GetMapping("/nuevo")
    public String nuevoReporte(Model model) {
        model.addAttribute("reportenuevo", new Reporte());
        model.addAttribute("areasnuevo", areaService.listarArea());
        return "reportes/crear";
    }

    // 🔹 Guardar nuevo reporte
    @PostMapping("/guardar")
    public String guardarReporte(@ModelAttribute Reporte reporte, HttpSession session) {
        Usuario usuarioLogueado = (Usuario) session.getAttribute("usuarioLogueado");
        if (usuarioLogueado == null) {
            return "redirect:/login";
        }

        // Asignar usuario logueado al reporte
        reporte.setUsuario(usuarioLogueado);

        // Guardar el reporte
        reporteService.guardar(reporte);

        return "redirect:/reportes/listar";
    }
    
    
}
