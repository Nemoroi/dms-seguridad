package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.Area;
import com.example.demo.repository.AreaRepository;
import com.example.demo.service.AreaService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AreaServiceImpl implements AreaService{

	private final AreaRepository areaRepository;
	
	public List<Area> listarArea(){
		return areaRepository.findAll();
	}
}
