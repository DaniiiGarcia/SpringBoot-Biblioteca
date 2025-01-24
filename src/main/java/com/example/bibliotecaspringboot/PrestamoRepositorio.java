package com.example.bibliotecaspringboot;

import com.example.bibliotecaspringboot.DTO.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrestamoRepositorio extends JpaRepository<Prestamo, Integer> {
}
