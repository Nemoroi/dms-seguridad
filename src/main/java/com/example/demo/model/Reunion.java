package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "Reunion")
@Getter @Setter
public class Reunion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reunionID;

    @ManyToOne
    @JoinColumn(name = "ReporteID", nullable = false)
    private Reporte reporte;

    @ManyToOne
    @JoinColumn(name = "ManagerID", nullable = false)
    private Usuario manager;

    @Column(name = "fecha_reunion", nullable = false)
    private LocalDateTime fechaReunion = LocalDateTime.now();

    @Column(columnDefinition = "TEXT")
    private String observaciones;

    
}