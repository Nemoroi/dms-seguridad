package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "Vistas")
@Getter @Setter
public class Vistas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer vistaID;

    @Column(nullable = false, length = 100)
    private String nombreVista;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @OneToMany(mappedBy = "vista")
    private List<RolVistas> rolVistas;


}