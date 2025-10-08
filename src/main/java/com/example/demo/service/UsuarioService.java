package com.example.demo.service;

import java.util.Optional;

import com.example.demo.model.Usuario;

public interface UsuarioService {
    Optional<Usuario> findByUsuario(String usuario);
    Usuario save(Usuario usuario);
}