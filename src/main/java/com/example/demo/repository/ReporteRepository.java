package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Reporte;

public interface ReporteRepository extends JpaRepository<Reporte, Integer>{
	 List<Reporte> findByUsuario_UsuarioID(Integer usuarioID);
}
