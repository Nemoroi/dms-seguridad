package com.example.demo.service;

import com.example.demo.model.PlanAccion;
import com.example.demo.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface PlanAccionService {

    PlanAccion save(PlanAccion planAccion);

    Optional<PlanAccion> findById(Integer id);

    List<PlanAccion> findAll();

    List<PlanAccion> findByReporteId(Integer reporteId);

    void deleteById(Integer id);
    
	List<PlanAccion> findByUsuarioaccion(Integer usuarioId);
}
