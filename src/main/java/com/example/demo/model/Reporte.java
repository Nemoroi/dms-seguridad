package com.example.demo.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Reporte")
@Getter @Setter
public class Reporte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reporteID;

    @ManyToOne
    @JoinColumn(name = "UsuarioID", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "AreaID", nullable = false)
    private Area area;

    @ManyToOne
    @JoinColumn(name = "ZonaID", nullable = false)
    private Zona zona;

    @ManyToOne
    @JoinColumn(name = "ManagerID", nullable = false)
    private Usuario manager;

    @Column(nullable = false, length = 150)
    private String titulo;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String descripcion;

    @Column(nullable = false)
    private LocalDateTime fechaReporte = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoReporte estado = EstadoReporte.Abierto;

    @OneToMany(mappedBy = "reporte")
    private List<Reunion> reuniones;

    public enum EstadoReporte {
        Abierto, EnRevisión, ConPlanesDeAcción, Cerrado
    }

}