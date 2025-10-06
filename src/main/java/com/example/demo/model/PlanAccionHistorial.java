package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Entity
@Table(name = "PlanAccion_Historial")
@Getter @Setter
public class PlanAccionHistorial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer historialID;

    @ManyToOne
    @JoinColumn(name = "PlanID", nullable = false)
    private PlanAccion plan;

    @ManyToOne
    @JoinColumn(name = "UsuarioID", nullable = false)
    private Usuario usuario;

    private String estadoAnterior;
    private String estadoNuevo;
    private LocalDateTime fechaCambio = LocalDateTime.now();

    @Column(columnDefinition = "TEXT")
    private String comentario;

}