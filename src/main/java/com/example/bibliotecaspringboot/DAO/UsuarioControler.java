package com.example.bibliotecaspringboot.DAO;

import com.example.bibliotecaspringboot.DTO.Usuario;
import com.example.bibliotecaspringboot.UsuariosRepositorio;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioControler{

    UsuariosRepositorio repoUser;

    public UsuarioControler() {
    }

    @Autowired
    public UsuarioControler(UsuariosRepositorio repoUser) {
        this.repoUser = repoUser;
    }

    //GET
    @GetMapping
    public ResponseEntity<List<Usuario>> getUsuario(){
        List<Usuario> usuarios = this.repoUser.findAll();
        System.out.println(usuarios);
        return ResponseEntity.ok(usuarios);
    }

    //Get by id
    @GetMapping("/{id}")
    public ResponseEntity<?> getUsuarioJson(@PathVariable Integer id){
        Usuario usuario = this.repoUser.findById(id).get();
        return ResponseEntity.ok(usuario);
    }

    //Post --> Insert
    @PostMapping
    public ResponseEntity<Usuario> addUsuario(@RequestBody Usuario usuario){
        //System.out.println("Entra aquí");
        Usuario usrPersist = this.repoUser.save(usuario);
        return ResponseEntity.ok().body(usrPersist);
    }

    //UPDATE
    @PutMapping()
    public ResponseEntity<Usuario> updateUsuario(@RequestBody Usuario usuario){
        repoUser.save(usuario);
        return ResponseEntity.ok(usuario);
    }

    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUsuario(@PathVariable Integer id){
        repoUser.deleteById(id);
        String mensaje = "Usuario con id: "+id+" borrado";
        return ResponseEntity.ok().body(mensaje);
    }

}
