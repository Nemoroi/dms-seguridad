package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "Area")
@Getter @Setter
public class Area {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer areaID;

    @Column(nullable = false, length = 100)
    private String nombreArea;

    @ManyToOne
    @JoinColumn(name = "JefeID", nullable = false)
    private Usuario jefe;

    @OneToMany(mappedBy = "area")
    private List<Zona> zonas;

    @OneToMany(mappedBy = "area")
    private List<Reporte> reportes;

}