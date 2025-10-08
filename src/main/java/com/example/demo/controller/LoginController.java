package com.example.demo.controller;

import com.example.demo.model.Usuario;
import com.example.demo.model.Vistas;
import com.example.demo.model.RolVistas;
import com.example.demo.service.UsuarioService;
import com.example.demo.service.VistaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("login")
public class LoginController {

    private final UsuarioService usuarioService;
    private final VistaService vistaService;

    // ✅ Cuando se ingresa a http://localhost:8080/login/ → va directo al login
    @GetMapping("/")
    public String inicio(HttpSession session) {
        if (session.getAttribute("usuarioLogueado") != null) {
            return "redirect:/login/inicio"; // redirige al inicio
        }
        return "index";
    }

    @GetMapping("/login")
    public String mostrarLogin() {
        return "index";
    }

    @PostMapping("/login")
    public String procesarLogin(
            @RequestParam String usuario,
            @RequestParam String clave,
            Model model,
            HttpSession session) {

        Optional<Usuario> userOpt = usuarioService.findByUsuario(usuario);

        if (userOpt.isPresent()) {
            Usuario u = userOpt.get();

            if (u.getClave().equals(clave) && u.getEstado().name().equals("Activo")) {

                // ✅ Guardar usuario en sesión
                session.setAttribute("usuarioLogueado", u);

             // ✅ Guardar las vistas y permisos 
                List<Vistas> vistasRol = vistaService.obtenerVistasPorRol(u.getRol().getRolID()); 
                session.setAttribute("vistasRol", vistasRol); 
                
                List<RolVistas> rolVistasUsuario = vistaService.obtenerRolVistasPorRol(u.getRol().getRolID()); 
                session.setAttribute("rolVistasUsuario", rolVistasUsuario);


                return "redirect:/login/inicio";
            }
        }

        model.addAttribute("error", "Usuario o contraseña incorrectos");
        return "index";
    }

    
    @GetMapping("/inicio")
    public String mostrarInicio(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
        

        if (usuario == null) {
            return "redirect:/login/login";
        }

        model.addAttribute("usuarioLogueado", usuario);
        return "home"; // 👈 tu archivo home.html
    }


    @GetMapping("/logout")
    public String cerrarSesion(HttpSession session) {
        session.invalidate();
        return "redirect:/login/login"; // redirige al login
    }
}
