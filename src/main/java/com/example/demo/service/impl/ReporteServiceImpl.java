package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.Reporte;
import com.example.demo.repository.ReporteRepository;
import com.example.demo.service.ReporteService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReporteServiceImpl implements ReporteService{
	
	private final ReporteRepository reporteRepository;
	
    @Override
    public List<Reporte> listarPorUsuario(Integer usuarioID) {
        return reporteRepository.findByUsuario_UsuarioID(usuarioID);
    }

}
