package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Reporte;
import com.example.demo.model.Reunion;
import com.example.demo.repository.ReunionRepository;
import com.example.demo.service.ReunionService;

@Service
public class ReunionServiceImp implements ReunionService{


    @Autowired
    private ReunionRepository reunionRepository;

    @Override
    public void guardar(Reunion reunion) {
    	reunionRepository.save(reunion);
    }

    @Override
    public List<Reunion> listarTodas() {
        return reunionRepository.findAll();
    }
    
    @Override
    public Reunion buscarPorReporte(Reporte reporte) {
        // Buscar la primera reunión que tenga este reporte
        return reunionRepository.findByReporte(reporte).orElse(null);
    }
	
}
