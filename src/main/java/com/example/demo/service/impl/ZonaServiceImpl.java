package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.Zona;
import com.example.demo.repository.ZonaRepository;
import com.example.demo.service.ZonaService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ZonaServiceImpl implements ZonaService{

		private final ZonaRepository zonaRepository;
		
		public List<Zona> listarZona(){
		 return zonaRepository.findAll();	
		}
}
