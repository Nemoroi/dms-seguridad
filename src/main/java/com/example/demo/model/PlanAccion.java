package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "Plan_Accion")
@Getter
@Setter
public class PlanAccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PlanID")
    private Integer planID;

    // Relación con Reporte
    @ManyToOne
    @JoinColumn(name = "ReporteID", nullable = false)
    private Reporte reporte;

    @Column(name = "Descripcion", nullable = false, columnDefinition = "TEXT")
    private String descripcion;

    // Manager asignado / creador
    @ManyToOne
    @JoinColumn(name = "ManagerID", nullable = false)
    private Usuario manager;

    // Responsable de ejecutar la acción
    @ManyToOne
    @JoinColumn(name = "ResponsableID", nullable = false)
    private Usuario responsable;

    @Column(name = "Fecha_Creacion", columnDefinition = "TIMESTAMP")
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    @Column(name = "Fecha_Compromiso")
    private LocalDate fechaCompromiso;

    // Datos de ejecución
    @Column(name = "Fecha_Ejecucion")
    private LocalDateTime fechaEjecucion;

    @Column(name = "Descripcion_Ejecucion", columnDefinition = "TEXT")
    private String descripcionEjecucion;

    @Column(name = "Evidencia", length = 255)
    private String evidencia;

    // Cierre por manager
    @Column(name = "FechaCierre")
    private LocalDateTime fechaCierre;

    @ManyToOne
    @JoinColumn(name = "Manager_CierraID")
    private Usuario managerCierra;

    @Enumerated(EnumType.STRING)
    @Column(name = "Estado", nullable = false)
    private EstadoPlan estado = EstadoPlan.Pendiente;

    public enum EstadoPlan {
        Pendiente,
        EnProceso,
        Ejecutado,
        Cerrado
    }
}
