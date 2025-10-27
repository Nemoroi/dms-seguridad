package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Reporte;
import com.example.demo.model.Reunion;

public interface ReunionService {
    void guardar(Reunion reunion);
    List<Reunion> listarTodas();
    Reunion buscarPorReporte(Reporte reporte);
}
