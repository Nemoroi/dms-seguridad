package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "PlanAccion")
@Getter @Setter
public class PlanAccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer planID;

    @ManyToOne
    @JoinColumn(name = "ReunionID", nullable = false)
    private Reunion reunion;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "ManagerID", nullable = false)
    private Usuario manager;

    @ManyToOne
    @JoinColumn(name = "ResponsableID", nullable = false)
    private Usuario responsable;

    private LocalDateTime fechaCreacion = LocalDateTime.now();
    private LocalDate fechaCompromiso;
    private LocalDateTime fechaEjecucion;
    private String descripcionEjecucion;
    private String evidencia;
    private LocalDateTime fechaCierre;

    @ManyToOne
    @JoinColumn(name = "ManagerCierraID")
    private Usuario managerCierra;

    @Enumerated(EnumType.STRING)
    private EstadoPlan estado = EstadoPlan.Pendiente;

    @OneToMany(mappedBy = "plan")
    private List<PlanAccionHistorial> historial;

    public enum EstadoPlan {
        Pendiente, EnProceso, Ejecutado, Cerrado
    }

}