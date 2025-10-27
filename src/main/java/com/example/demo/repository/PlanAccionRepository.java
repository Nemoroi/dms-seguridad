package com.example.demo.repository;

import com.example.demo.model.PlanAccion;
import com.example.demo.model.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanAccionRepository extends JpaRepository<PlanAccion, Integer> {

    List<PlanAccion> findByReporte_ReporteID(Integer reporteId);
    


    @Query("SELECT p FROM PlanAccion p WHERE p.responsable.usuarioID = :id")
    List<PlanAccion> findByResponsableId(@Param("id") Integer id);

}
