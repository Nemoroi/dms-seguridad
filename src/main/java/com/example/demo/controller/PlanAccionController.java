package com.example.demo.controller;

import com.example.demo.model.PlanAccion;
import com.example.demo.model.Reporte;
import com.example.demo.model.Reunion;
import com.example.demo.model.Usuario;
import com.example.demo.service.PlanAccionService;
import com.example.demo.service.ReporteService;
import com.example.demo.service.ReunionService;
import com.example.demo.service.UsuarioService;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/planes")
public class PlanAccionController {

    private final PlanAccionService planAccionService;
    private final ReporteService reporteService;
    private final UsuarioService usuarioService;
    private final ReunionService reunionService;

    public PlanAccionController(PlanAccionService planAccionService,
                                ReporteService reporteService,
                                UsuarioService usuarioService,
                                ReunionService reunionService) {
        this.planAccionService = planAccionService;
        this.reporteService = reporteService;
        this.usuarioService = usuarioService;
        this.reunionService = reunionService;
    }

    // Abrir modal de Plan de Acción
    @GetMapping("/crear/{reporteId}")
    public String crearPlan(@PathVariable Integer reporteId, Model model) {

        Reporte reporte = reporteService.buscarPorId(reporteId);
        PlanAccion planAccion = new PlanAccion();
        planAccion.setReporte(reporte);

        List<Usuario> usuarios = usuarioService.listarUsuariosList();   // Responsables
        List<Usuario> managers = usuarioService.listarManagers();      // Managers

        model.addAttribute("planAccion", planAccion);
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("managers", managers);
        model.addAttribute("reporte", reporte);

        return "detalle-reporte"; // Página donde se abrirá el modal
    }

    @PostMapping("/guardar")
    public String guardarPlan(@ModelAttribute PlanAccion planAccion,
                              @RequestParam("managerId") Integer managerId,
                              @ModelAttribute Reunion reunion) {

        // Obtener manager completo
        Usuario manager = usuarioService.buscarPorId(managerId);
        planAccion.setManager(manager);

        // Obtener responsable completo
        Integer responsableId = planAccion.getResponsable().getUsuarioID();
        Usuario responsable = usuarioService.buscarPorId(responsableId);
        planAccion.setResponsable(responsable);

        // Guardar plan de acción primero
        planAccionService.save(planAccion);

        // Verificar si ya existe reunión para este reporte
        Reunion reunionExistente = reunionService.buscarPorReporte(planAccion.getReporte());

        if (reunionExistente == null) {
            // No existe: crear nueva reunión
            reunion.setManager(manager);
            reunion.setReporte(planAccion.getReporte());
            reunion.setObservaciones(planAccion.getDescripcion()); // Primera descripción
            reunionService.guardar(reunion);
        } else {
            // Ya existe: acumular la descripción del nuevo plan
            String observacionesActuales = reunionExistente.getObservaciones();
            if (observacionesActuales == null || observacionesActuales.isEmpty()) {
                reunionExistente.setObservaciones(planAccion.getDescripcion());
            } else {
                reunionExistente.setObservaciones(observacionesActuales 
                    + "\n- " + planAccion.getDescripcion());
            }
            reunionService.guardar(reunionExistente);
        }

        // Redirigir
        Integer reporteId = planAccion.getReporte().getReporteID();
        return "redirect:/reuniones/crear/" + reporteId;
    }

    @GetMapping("/listarPlanes")
    public String listarPlanes(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
        
        Integer usuarioId = usuario.getUsuarioID();

        List<PlanAccion> planes = planAccionService.findByUsuarioaccion(usuarioId);
        model.addAttribute("planes", planes);

        return "/planes_accion/listar"; // Thymeleaf template: templates/planes.html
    }

    
    @GetMapping("/verPlan/{id}")
    public String verPlan(@PathVariable Integer id, Model model) {
        PlanAccion plan = planAccionService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Plan no encontrado: " + id));
        model.addAttribute("plan", plan);
        return "/planes_accion/editar"; // Thymeleaf template que crearemos
    }
    
    @PostMapping("/{id}/guardar")
    public String actualizarPlan(@PathVariable Integer id,
                                 @RequestParam("descripcionEjecucion") String descripcionEjecucion,
                                 @RequestParam("evidencia") String evidencia,
                                 @RequestParam("estado") PlanAccion.EstadoPlan estado) {

        // Buscar plan existente
        PlanAccion planExistente = planAccionService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Plan no encontrado: " + id));

        // Actualizar campos
        planExistente.setDescripcionEjecucion(descripcionEjecucion);
        planExistente.setEvidencia(evidencia);
        planExistente.setEstado(estado);
        planExistente.setFechaEjecucion(LocalDateTime.now()); // Registrar fecha de ejecución

        // Guardar (update)
        planAccionService.save(planExistente);

        // Redirigir a listado
        return "redirect:/planes/listarPlanes";
    }



}
