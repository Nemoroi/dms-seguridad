package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Zona;

@Repository
public interface ZonaRepository extends JpaRepository<Zona, Integer>{

}
