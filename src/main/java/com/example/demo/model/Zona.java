package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "Zona")
@Getter @Setter
public class Zona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer zonaID;

    @Column(nullable = false, length = 100)
    private String nombreZona;

    @ManyToOne
    @JoinColumn(name = "AreaID", nullable = false)
    private Area area;

    @OneToMany(mappedBy = "zona")
    private List<Reporte> reportes;


}