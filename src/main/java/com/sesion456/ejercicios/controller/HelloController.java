package com.sesion456.ejercicios.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/saludo")
public class HelloController {

    @GetMapping
    public String saludar(){
        return "Hola Open-Bootcamp !";

    }
}
