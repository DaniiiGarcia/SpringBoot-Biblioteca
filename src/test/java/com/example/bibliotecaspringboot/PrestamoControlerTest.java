package com.example.bibliotecaspringboot;

import com.example.bibliotecaspringboot.DTO.Prestamo;
import com.example.bibliotecaspringboot.PrestamoRepositorio;
import com.example.bibliotecaspringboot.DAO.PrestamoControler;
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

public class PrestamoControlerTest {

    @Mock
    private PrestamoRepositorio repositorioPrestamo;

    @InjectMocks
    private PrestamoControler prestamoControler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetPrestamos() {
        Prestamo prestamo1 = new Prestamo();
        Prestamo prestamo2 = new Prestamo();
        List<Prestamo> listaPrestamos = Arrays.asList(prestamo1, prestamo2);

        when(repositorioPrestamo.findAll()).thenReturn(listaPrestamos);

        ResponseEntity<List<Prestamo>> response = prestamoControler.getPrestamos();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(listaPrestamos, response.getBody());
        verify(repositorioPrestamo, times(1)).findAll();
    }

    @Test
    void testGetPrestamo() {
        Prestamo prestamo = new Prestamo();
        when(repositorioPrestamo.findById(anyInt())).thenReturn(Optional.of(prestamo));

        ResponseEntity<Prestamo> response = prestamoControler.getPrestamo(1);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(prestamo, response.getBody());
        verify(repositorioPrestamo, times(1)).findById(1);
    }

    @Test
    void testAddPrestamo() {
        Prestamo prestamo = new Prestamo();
        when(repositorioPrestamo.save(any(Prestamo.class))).thenReturn(prestamo);

        ResponseEntity<?> response = prestamoControler.addPrestamo(prestamo);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(prestamo, response.getBody());
        verify(repositorioPrestamo, times(1)).save(prestamo);
    }

    @Test
    void testUpdatePrestamo() {
        Prestamo prestamo = new Prestamo();
        when(repositorioPrestamo.save(any(Prestamo.class))).thenReturn(prestamo);

        ResponseEntity<?> response = prestamoControler.updatePrestamo(prestamo);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(prestamo, response.getBody());
        verify(repositorioPrestamo, times(1)).save(prestamo);
    }

    @Test
    void testDeletePrestamo() {
        Integer id = 1;

        doNothing().when(repositorioPrestamo).deleteById(anyInt());

        ResponseEntity<String> response = prestamoControler.deletePrestamo(id);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Prestamo con id: " + id + " borrado", response.getBody());
        verify(repositorioPrestamo, times(1)).deleteById(id);
    }
}
