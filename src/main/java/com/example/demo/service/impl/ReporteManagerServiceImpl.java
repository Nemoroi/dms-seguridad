package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.Reporte;
import com.example.demo.repository.ReporteManagerRepository;
import com.example.demo.service.ReporteManagerService;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class ReporteManagerServiceImpl implements ReporteManagerService{
	
	private final ReporteManagerRepository reporteRepository;
	
	public List<Reporte> listarreporte(){
		return reporteRepository.findAll();
	}

}
