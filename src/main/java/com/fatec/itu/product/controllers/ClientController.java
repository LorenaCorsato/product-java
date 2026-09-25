package com.fatec.itu.product.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fatec.itu.product.entities.Client;
import com.fatec.itu.product.services.ClientService;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


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

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

     @PostMapping
    public ResponseEntity<Client> save(@RequestBody Client client)
    {
        Client c = service.save(client);
       
        URI location = ServletUriComponentsBuilder
                       .fromCurrentRequest()
                       .path("/{id}")
                       .buildAndExpand(c.getId())
                       .toUri();
        return ResponseEntity.created(location).body(c);


    }

    @PutMapping("{id}")
    public ResponseEntity<Void> update(@PathVariable long id,
                                       @RequestBody Client client)
    {
            service.update(client, id);
            return ResponseEntity.noContent().build();
    }
}
