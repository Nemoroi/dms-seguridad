package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Reporte;

public interface ReporteService {
    List<Reporte> listarPorUsuario(Integer usuarioID);
    List<Reporte> listarTodos();
    Reporte buscarPorId(Integer reporteID);
    Reporte guardar(Reporte reporte);
}
