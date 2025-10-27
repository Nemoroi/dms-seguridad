package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Reporte;
import com.example.demo.model.Reunion;

public interface ReunionRepository extends JpaRepository<Reunion, Integer>{
	 Optional<Reunion> findByReporte(Reporte reporte);
}
