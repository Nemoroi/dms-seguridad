package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "Rol")
@Getter @Setter
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer rolID;

    @Column(nullable = false, unique = true, length = 50)
    private String nombreRol;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    // Relaciones
    @OneToMany(mappedBy = "rol")
    private List<Usuario> usuarios;

    @OneToMany(mappedBy = "rol")
    private List<RolVistas> rolVistas;

}