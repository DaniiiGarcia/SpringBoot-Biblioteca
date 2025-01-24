package com.example.bibliotecaspringboot.DAO;

import com.example.bibliotecaspringboot.DTO.Ejemplar;
import com.example.bibliotecaspringboot.EjemplaresRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ejemplares")
public class EjemplarControler {

    EjemplaresRepositorio repoEjemplar;

    public EjemplarControler() {
    }

    @Autowired
    public EjemplarControler(EjemplaresRepositorio repoEjemplar) {
        this.repoEjemplar = repoEjemplar;
    }

    //GET ALL
    @GetMapping
    public ResponseEntity<List<Ejemplar>> getEjemplares() {
        List<Ejemplar> ejemplares = this.repoEjemplar.findAll();
        System.out.println(ejemplares);
        return ResponseEntity.ok(ejemplares);
    }

    //GET EJEMPLAR POR ID
    @GetMapping("/{id}")
    public ResponseEntity<Ejemplar> getEjemplarXID(@PathVariable Integer id) {
        Ejemplar ejemplar = repoEjemplar.findById(id).get();
        return ResponseEntity.ok(ejemplar);
    }

    //INSERT
    @PostMapping
    public ResponseEntity<Ejemplar> addEjemplar(@RequestBody Ejemplar ejemplar) {
        Ejemplar ejemplar1 = repoEjemplar.save(ejemplar);
        return ResponseEntity.ok().body(ejemplar1);
    }

    //UPDATE
    @PutMapping()
    public ResponseEntity<Ejemplar> updateEjemplar(@RequestBody Ejemplar ejemplar) {
        repoEjemplar.save(ejemplar);
        return ResponseEntity.ok(ejemplar);
    }

    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEjemplar(@PathVariable Integer id) {
        repoEjemplar.deleteById(id);
        String message = "Ejemplar con id "+id+" eliminado";
        return ResponseEntity.ok().body(message);
    }

}