package com.fatec.itu.product.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fatec.itu.product.dtos.ProductRequest;
import com.fatec.itu.product.dtos.ProductResponse;
import com.fatec.itu.product.services.ProductService;

//Definindo que é um controller, fazendo conseguir lidar com chamadas http
@RestController 
@RequestMapping ("/products")
public class ProductController {

    //Injetando service (dependência)
    @Autowired
    private ProductService service;


    //EndPoint - recebe lista de produtos(http://localhost:8080/products) e  chama o service.findAll
    @GetMapping
    // ResponseEntity<List<Product>> quer dizer que o corpo da requisição retorna uma lista de produtos (JSON)
    public ResponseEntity<List<ProductResponse>> getAll() {
        return ResponseEntity.ok(service.findAll()); // Devolve uma LISTA de produto
    }                                           

    //Primeira exposição

    //EndPoint - recebe id pela url (http://localhost:8080/products/{id}) e  chama o service.findById
    @GetMapping("{id}")
    // ResponseEntity<Product> quer dizer que o corpo da requisição retorna um produto (JSON)
    public ResponseEntity<ProductResponse> getById(@PathVariable long id){ // Devolve UM OBJETO PRODUTO
        // Vai retornar o estado code - OK (200)
        return ResponseEntity.ok(service.findById(id));
    }

    @DeleteMapping("{id}")
    // ResponseEntity<void> quer dizer que o corpo da requisição não retorna nada
    public ResponseEntity<Void> deleteById(@PathVariable long id) {
        service.deleteById(id);
        // Vai retornar o estado code - No Content (204)
        return ResponseEntity.noContent().build();
    }

     @PostMapping
     //Passa um produto no corpo da requisição e devolve um produto no corpo da resposta
    public ResponseEntity<ProductResponse> save(@RequestBody ProductRequest product)
    {
        ProductResponse p = service.save(product);
       
        //Localização/caminho do produto criado 
        URI location = ServletUriComponentsBuilder
                       .fromCurrentRequest()
                       .path("/{id}")
                       .buildAndExpand(p.id())
                       .toUri();
        // Vai retornar o estado code - Created (201)
        return ResponseEntity.created(location).body(p);


    }

    @PutMapping("{id}")
    public ResponseEntity<Void> update(@PathVariable long id,//Passa o ID do produto e os dados
                                       @RequestBody ProductRequest product)
    {
            service.update(product, id);
            // Vai retornar o estado code - No Content (204)
            return ResponseEntity.noContent().build();
    }
}
