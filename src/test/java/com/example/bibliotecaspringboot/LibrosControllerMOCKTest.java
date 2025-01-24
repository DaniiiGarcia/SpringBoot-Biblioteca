package com.example.bibliotecaspringboot;

import com.example.bibliotecaspringboot.DTO.Libro;
import com.example.bibliotecaspringboot.LibrosRepository;
import com.example.bibliotecaspringboot.DAO.LibrosControllerMOCK;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class LibrosControllerMOCKTest {

    @Mock
    private LibrosRepository repositorioLibros;

    @InjectMocks
    private LibrosControllerMOCK librosController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetLibro() {
        Libro libro1 = new Libro();
        Libro libro2 = new Libro();
        List<Libro> listaLibros = Arrays.asList(libro1, libro2);

        when(repositorioLibros.findAll()).thenReturn(listaLibros);

        ResponseEntity<List<Libro>> response = librosController.getLibro();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(listaLibros, response.getBody());
        verify(repositorioLibros, times(1)).findAll();
    }

    @Test
    void testGetLibroJson() {
        Libro libro = new Libro();
        when(repositorioLibros.findById(anyString())).thenReturn(Optional.of(libro));

        ResponseEntity<Libro> response = librosController.getLibroJson("isbn");

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(libro, response.getBody());
        verify(repositorioLibros, times(1)).findById("isbn");
    }

    @Test
    void testAddLibro() {
        Libro libro = new Libro();
        when(repositorioLibros.save(any(Libro.class))).thenReturn(libro);

        ResponseEntity<Libro> response = librosController.addLibro(libro);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(libro, response.getBody());
        verify(repositorioLibros, times(1)).save(libro);
    }

    @Test
    void testAddLibroForm() {
        Libro libro = new Libro();
        when(repositorioLibros.save(any(Libro.class))).thenReturn(libro);

        ResponseEntity<Libro> response = librosController.addLibroForm("isbn", "titulo", "autor");

        assertEquals(201, response.getStatusCodeValue());
        assertEquals(libro, response.getBody());
        verify(repositorioLibros, times(1)).save(any(Libro.class));
    }

    @Test
    void testAddLibroFormFichero() {
        Libro libro = new Libro();
        MultipartFile imagen = mock(MultipartFile.class);
        when(repositorioLibros.save(any(Libro.class))).thenReturn(libro);

        ResponseEntity<Libro> response = librosController.addLibroFormFichero("isbn", "titulo", "autor", imagen);

        assertEquals(201, response.getStatusCodeValue());
        assertEquals(libro, response.getBody());
        verify(repositorioLibros, times(1)).save(any(Libro.class));
    }

    @Test
    void testUpdateLibro() {
        Libro libro = new Libro();
        when(repositorioLibros.save(any(Libro.class))).thenReturn(libro);

        ResponseEntity<Libro> response = librosController.updateLibro(libro, "isbn");

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(libro, response.getBody());
        verify(repositorioLibros, times(1)).save(libro);
    }

    @Test
    void testDeleteLibro() {
        doNothing().when(repositorioLibros).deleteById(anyString());

        ResponseEntity<String> response = librosController.deleteLibro("isbn");

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("libro con isbn: isbn borrado", response.getBody());
        verify(repositorioLibros, times(1)).deleteById("isbn");
    }

    @Test
    void testTestEndpoint() {
        ResponseEntity<String> response = librosController.testEndpoint();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Funcionando", response.getBody());
    }
}
