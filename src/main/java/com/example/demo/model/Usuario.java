package com.example.demo.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Usuario")
@Getter @Setter
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer usuarioID;

    @Column(nullable = false, length = 100)
    private String nombreCompleto;

    @Column(nullable = false, unique = true, length = 50)
    private String usuario;

    @Column(nullable = false, length = 255)
    private String clave;

    @Column(unique = true, length = 100)
    private String email;

    @Column(length = 20)
    private String telefono;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoUsuario estado = EstadoUsuario.Activo;

    @Column(nullable = false)
    private LocalDateTime fechaRegistro = LocalDateTime.now();


    @ManyToOne
    @JoinColumn(name = "RolID", nullable = false)
    private Rol rol;

    @OneToMany(mappedBy = "jefe")
    private List<Area> areasJefe;

    @OneToMany(mappedBy = "usuario")
    private List<Reporte> reportes;

    @OneToMany(mappedBy = "manager")
    private List<Reporte> reportesManager;

    // Getters y Setters

    public enum EstadoUsuario {
        Activo, Inactivo
    }
}