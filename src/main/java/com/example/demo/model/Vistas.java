package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "vistas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vistas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "VistaID")
    private Integer vistaID;

    @Column(name = "Nombre_Vista", nullable = false)
    private String nombreVista;

    @Column(name = "Descripcion")
    private String descripcion;
    
    @Column(name = "Ruta")
    private String ruta;

    @OneToMany(mappedBy = "vista")
    private List<RolVistas> rolVistas;


}