package com.example.bibliotecaspringboot;

import com.example.bibliotecaspringboot.DTO.Ejemplar;
import com.example.bibliotecaspringboot.DAO.EjemplarControler;
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

public class EjemplarControlerTest {

    @Mock
    private EjemplaresRepositorio repoEjemplar;

    @InjectMocks
    private EjemplarControler ejemplarControler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetEjemplares() {
        Ejemplar ejemplar1 = new Ejemplar();
        Ejemplar ejemplar2 = new Ejemplar();
        List<Ejemplar> ejemplares = Arrays.asList(ejemplar1, ejemplar2);

        when(repoEjemplar.findAll()).thenReturn(ejemplares);

        ResponseEntity<List<Ejemplar>> response = ejemplarControler.getEjemplares();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(ejemplares, response.getBody());
        verify(repoEjemplar, times(1)).findAll();
    }

    @Test
    void testGetEjemplarXID() {
        Ejemplar ejemplar = new Ejemplar();
        when(repoEjemplar.findById(anyInt())).thenReturn(Optional.of(ejemplar));

        ResponseEntity<Ejemplar> response = ejemplarControler.getEjemplarXID(1);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(ejemplar, response.getBody());
        verify(repoEjemplar, times(1)).findById(1);
    }

    @Test
    void testAddEjemplar() {
        Ejemplar ejemplar = new Ejemplar();
        when(repoEjemplar.save(any(Ejemplar.class))).thenReturn(ejemplar);

        ResponseEntity<Ejemplar> response = ejemplarControler.addEjemplar(ejemplar);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(ejemplar, response.getBody());
        verify(repoEjemplar, times(1)).save(ejemplar);
    }

    @Test
    void testUpdateEjemplar() {
        Ejemplar ejemplar = new Ejemplar();
        when(repoEjemplar.save(any(Ejemplar.class))).thenReturn(ejemplar);

        ResponseEntity<Ejemplar> response = ejemplarControler.updateEjemplar(ejemplar);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(ejemplar, response.getBody());
        verify(repoEjemplar, times(1)).save(ejemplar);
    }

    @Test
    void testDeleteEjemplar() {
        Integer id = 1;

        doNothing().when(repoEjemplar).deleteById(anyInt());

        ResponseEntity<String> response = ejemplarControler.deleteEjemplar(id);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Ejemplar con id " + id + " eliminado", response.getBody());
        verify(repoEjemplar, times(1)).deleteById(id);
    }
}
