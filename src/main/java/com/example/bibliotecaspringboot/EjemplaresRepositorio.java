package com.example.bibliotecaspringboot;

import com.example.bibliotecaspringboot.DTO.Ejemplar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EjemplaresRepositorio extends JpaRepository<Ejemplar, Integer> {

}
