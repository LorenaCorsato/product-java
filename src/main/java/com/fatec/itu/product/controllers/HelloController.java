package com.fatec.itu.product.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")    //    localhost:8080/hello - mapeamento
public class HelloController {

    @GetMapping //Método Get do http - está recebendo o get - métodos do protocolo http (get, post,put, patch e delete)
    public String hello() {
        return "Hello, World!";
    }
    
}
