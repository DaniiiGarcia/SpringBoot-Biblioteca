package com.example.bibliotecaspringboot;

import com.example.bibliotecaspringboot.DTO.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuariosRepositorio extends JpaRepository<Usuario,Integer> {
}
