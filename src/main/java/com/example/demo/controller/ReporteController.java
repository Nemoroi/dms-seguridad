package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.Reporte;
import com.example.demo.model.Usuario;
import com.example.demo.service.ReporteService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/reportes")
public class ReporteController {

    @Autowired
    private ReporteService reporteService;

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
}
