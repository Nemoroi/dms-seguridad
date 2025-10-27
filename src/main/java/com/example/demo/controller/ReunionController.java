package com.example.demo.controller;

import com.example.demo.model.Reunion;
import com.example.demo.model.PlanAccion;
import com.example.demo.model.Reporte;
import com.example.demo.model.Usuario;
import com.example.demo.service.ReunionService;
import com.example.demo.service.ReporteService;
import com.example.demo.service.UsuarioService;

import jakarta.servlet.http.HttpSession;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/reuniones")
public class ReunionController {

    @Autowired
    private ReunionService reunionService;

    @Autowired
    private ReporteService reporteService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/crear/{reporteId}")
    public String crearReunion(HttpSession session, @PathVariable Integer reporteId, Model model) {

        // Usuario logueado (manager por defecto)
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");

        // Obtener reporte
        Reporte reporte = reporteService.buscarPorId(reporteId);

        // ⚡ Objeto para el modal de Plan de Acción
        PlanAccion planAccion = new PlanAccion();
        planAccion.setReporte(reporte);

        // Buscar si ya existe una reunión para este reporte
        Reunion reunion = reunionService.buscarPorReporte(reporte);
        if (reunion == null) {
            // Si no existe, crear nueva reunión
            reunion = new Reunion();
            reunion.setReporte(reporte);
            reunion.setManager(usuario);                  // Asignar manager
            reunion.setFechaReunion(LocalDateTime.now()); // Fecha por defecto
            reunion.setObservaciones("");                 // Inicializar vacío
        }

        // Listas para selects del modal
        List<Usuario> usuarios = usuarioService.listarUsuariosList();  // responsables
        List<Usuario> managers = usuarioService.listarManagers();      // managers

        // Agregar atributos al modelo
        model.addAttribute("planAccion", planAccion);
        model.addAttribute("reunion", reunion);
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("managers", managers);
        model.addAttribute("reporte", reporte);

        // Para mostrar el nombre del manager en el modal
        model.addAttribute("managerNombre", reunion.getManager().getNombreCompleto());
        model.addAttribute("managerId", reunion.getManager().getUsuarioID());

        return "reuniones/crear";
    }


    // ✅ Guardar reunión
    @PostMapping("/guardar")
    public String guardarReunion(@ModelAttribute("reunion") Reunion reunion) {
        reunionService.guardar(reunion);
        return "redirect:/reportesmanager/listar";
    }
}
