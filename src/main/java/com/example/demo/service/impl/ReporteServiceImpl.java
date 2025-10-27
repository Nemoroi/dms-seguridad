package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Reporte guardar(Reporte reporte) {
        return reporteRepository.save(reporte);
    }
    
    @Override
    public List<Reporte> listarTodos() {
        return reporteRepository.findAll();
    }

    // ✅ Para buscar un reporte por ID (necesario para /reuniones/crear/{reporteID})
    @Override
    public Reporte buscarPorId(Integer reporteID) {
        Optional<Reporte> optional = reporteRepository.findById(reporteID);
        return optional.orElse(null);
    }
    
}
