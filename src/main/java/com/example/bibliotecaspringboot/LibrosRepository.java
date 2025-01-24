package com.example.bibliotecaspringboot;

import com.example.bibliotecaspringboot.DTO.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibrosRepository extends JpaRepository<Libro, String> {

}
