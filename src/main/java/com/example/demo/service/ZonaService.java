package com.example.demo.service;

import java.util.List;
import com.example.demo.model.Zona;

public interface ZonaService {
	List<Zona> listarZona();
	List<Zona> listarPorArea(Integer areaId);
}
