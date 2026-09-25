package com.fatec.itu.product.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fatec.itu.product.entities.Client;
import com.fatec.itu.product.services.ClientService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController 
@RequestMapping ("/clients")
public class ClientController {
    
    private final ClientService service;
    
    ClientController (ClientService service){
        this.service = service;
    }

    @GetMapping
     public ResponseEntity<List<Client>> getAll() {
        return ResponseEntity.ok(service.findAll()); 
    }

    @GetMapping("{id}")
    public ResponseEntity<Client> getById(@PathVariable long id){ 
        return ResponseEntity.ok(service.findById(id));
    }
    
}
