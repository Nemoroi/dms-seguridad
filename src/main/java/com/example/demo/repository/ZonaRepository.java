package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Zona;

@Repository
public interface ZonaRepository extends JpaRepository<Zona, Integer>{
	 List<Zona> findByAreaAreaID(Integer areaID);
}
