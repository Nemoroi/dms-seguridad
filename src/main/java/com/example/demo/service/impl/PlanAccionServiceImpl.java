package com.example.demo.service.impl;

import com.example.demo.model.PlanAccion;
import com.example.demo.model.Usuario;
import com.example.demo.repository.PlanAccionRepository;
import com.example.demo.service.PlanAccionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlanAccionServiceImpl implements PlanAccionService {

    private final PlanAccionRepository planAccionRepository;

    public PlanAccionServiceImpl(PlanAccionRepository planAccionRepository) {
        this.planAccionRepository = planAccionRepository;
    }

    @Override
    public PlanAccion save(PlanAccion planAccion) {
        return planAccionRepository.save(planAccion);
    }

    @Override
    public Optional<PlanAccion> findById(Integer id) {
        return planAccionRepository.findById(id);
    }

    @Override
    public List<PlanAccion> findAll() {
        return planAccionRepository.findAll();
    }

    @Override
    public List<PlanAccion> findByReporteId(Integer reporteId) {
        return planAccionRepository.findByReporte_ReporteID(reporteId);
    }

    @Override
    public void deleteById(Integer id) {
        planAccionRepository.deleteById(id);
    }
    
    @Override
    public List<PlanAccion> findByUsuarioaccion(Integer usuarioId) {
        // Solo planes donde el usuario es responsable
        return planAccionRepository.findByResponsableId(usuarioId);
    }

}
