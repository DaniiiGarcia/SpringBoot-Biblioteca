package com.example.bibliotecaspringboot;

import com.example.bibliotecaspringboot.DTO.Usuario;
import com.example.bibliotecaspringboot.UsuariosRepositorio;
import com.example.bibliotecaspringboot.DAO.UsuarioControler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UsuarioControlerTest {

    @Mock
    private UsuariosRepositorio repoUser;

    @InjectMocks
    private UsuarioControler usuarioControler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetUsuario() {
        Usuario usuario1 = new Usuario();
        Usuario usuario2 = new Usuario();
        List<Usuario> listaUsuarios = Arrays.asList(usuario1, usuario2);

        when(repoUser.findAll()).thenReturn(listaUsuarios);

        ResponseEntity<List<Usuario>> response = usuarioControler.getUsuario();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(listaUsuarios, response.getBody());
        verify(repoUser, times(1)).findAll();
    }

    @Test
    void testGetUsuarioJson() {
        Usuario usuario = new Usuario();
        when(repoUser.findById(anyInt())).thenReturn(Optional.of(usuario));

        ResponseEntity<?> response = usuarioControler.getUsuarioJson(1);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(usuario, response.getBody());
        verify(repoUser, times(1)).findById(1);
    }

    @Test
    void testAddUsuario() {
        Usuario usuario = new Usuario();
        when(repoUser.save(any(Usuario.class))).thenReturn(usuario);

        ResponseEntity<Usuario> response = usuarioControler.addUsuario(usuario);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(usuario, response.getBody());
        verify(repoUser, times(1)).save(usuario);
    }

    @Test
    void testUpdateUsuario() {
        Usuario usuario = new Usuario();
        when(repoUser.save(any(Usuario.class))).thenReturn(usuario);

        ResponseEntity<Usuario> response = usuarioControler.updateUsuario(usuario);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(usuario, response.getBody());
        verify(repoUser, times(1)).save(usuario);
    }

    @Test
    void testDeleteUsuario() {
        Integer id = 1;

        doNothing().when(repoUser).deleteById(anyInt());

        ResponseEntity<String> response = usuarioControler.deleteUsuario(id);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Usuario con id: " + id + " borrado", response.getBody());
        verify(repoUser, times(1)).deleteById(id);
    }
}
