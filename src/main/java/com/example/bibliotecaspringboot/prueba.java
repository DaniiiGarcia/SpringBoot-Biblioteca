package com.example.bibliotecaspringboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class prueba {

    @GetMapping("/hola")
    public String hola() {
        return "hola";
    }

}
