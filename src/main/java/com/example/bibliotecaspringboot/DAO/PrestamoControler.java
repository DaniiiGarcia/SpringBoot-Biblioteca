package com.example.bibliotecaspringboot.DAO;

import com.example.bibliotecaspringboot.DTO.Ejemplar;
import com.example.bibliotecaspringboot.DTO.Prestamo;
import com.example.bibliotecaspringboot.DTO.Usuario;
import com.example.bibliotecaspringboot.PrestamoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prestamo")
public class PrestamoControler {

    PrestamoRepositorio repositorioPrestamo;

    public PrestamoControler() {
    }

    @Autowired
    public PrestamoControler(PrestamoRepositorio repositorioPrestamo) {
        this.repositorioPrestamo = repositorioPrestamo;
    }

    //ReadAll
    @GetMapping
    public ResponseEntity<List<Prestamo>> getPrestamos(){
        List<Prestamo> ejemplares = repositorioPrestamo.findAll();
        System.out.println(ejemplares);
        return ResponseEntity.ok(ejemplares);
    }

    //Read por id
    @GetMapping("/{id}")
    public ResponseEntity<Prestamo> getPrestamo(@PathVariable Integer id){
        Prestamo p = repositorioPrestamo.findById(id).get();
        return ResponseEntity.ok(p);
    }

    //INSERT
    @PostMapping
    public ResponseEntity<?> addPrestamo(@RequestBody Prestamo prestamo){
        //System.out.println("Entra aquí");
        Prestamo prestamoPersist = this.repositorioPrestamo.save(prestamo);
        return ResponseEntity.ok().body(prestamoPersist);
    }

    //UPDATE
    @PutMapping()
    public ResponseEntity<?> updatePrestamo(@RequestBody Prestamo prestamo){
        repositorioPrestamo.save(prestamo);
        return ResponseEntity.ok(prestamo);
    }

    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePrestamo(@PathVariable Integer id){
        repositorioPrestamo.deleteById(id);
        String mensaje = "Prestamo con id: "+id+" borrado";
        return ResponseEntity.ok().body(mensaje);
    }


}
