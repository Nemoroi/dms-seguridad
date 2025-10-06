package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Entity
@Table(name = "Rol_Vista", uniqueConstraints = @UniqueConstraint(columnNames = {"RolID", "VistaID"}))
@Getter @Setter
public class RolVistas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer rolVistaID;

    @ManyToOne
    @JoinColumn(name = "RolID", nullable = false)
    private Rol rol;

    @ManyToOne
    @JoinColumn(name = "VistaID", nullable = false)
    private Vistas vista;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Permiso permiso = Permiso.Lectura;

    @Column(nullable = false)
    private LocalDateTime fechaAsignacion = LocalDateTime.now();

    public enum Permiso {
        Lectura, Escritura, Admin
    }


}