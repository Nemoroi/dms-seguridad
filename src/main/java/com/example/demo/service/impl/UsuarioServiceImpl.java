package com.example.demo.service.impl;


import com.example.demo.model.Usuario;

import com.example.demo.repository.UsuarioRepository;
import com.example.demo.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public Optional<Usuario> findByUsuario(String usuario) {
        return usuarioRepository.findByUsuario(usuario);
    }


    @Override
    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public List<Usuario> listarUsuariosList() {
        return usuarioRepository.findAll();
    }
    
    // ✅ Nuevo método: listar solo los Managers
    @Override
    public List<Usuario> listarManagers() {
        return usuarioRepository.findByRol_NombreRol("Manager");
    }

    @Override
    public Usuario buscarPorId(Integer id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));
    }


    }
