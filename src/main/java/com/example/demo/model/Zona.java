package com.example.demo.model;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Zona")
@Getter @Setter
@JsonIgnoreProperties({"reportes", "area"}) // ← evita recursión
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
