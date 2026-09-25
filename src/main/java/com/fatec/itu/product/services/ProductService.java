package com.fatec.itu.product.services;


import java.util.List;

import org.springframework.stereotype.Service;

import com.fatec.itu.product.entities.Product;
import com.fatec.itu.product.repositories.ProductRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductService {

  private final ProductRepository repository;

    //Método construtor - é aqui que a injeção acontece
    ProductService(ProductRepository repository) {
        this.repository = repository;
    } 
    

    // Primeiro service - pesquisa produto pelo id
     public Product findById(Long id) {
        return repository.findById(id) // Devolve UM OBJETO PRODUTO
                         .orElseThrow(() -> new EntityNotFoundException()); // trata exceção/erro
    }

    // Lista de todos os produtos
    public List<Product> findAll(){
      return repository.findAll();
    }


    public void deleteById(long id) {
      //Primeiro verifica se existe algum produto com o id, se existir deleta se não retorna uma mensagem de erro
      if(repository.existsById(id))
            repository.deleteById(id);
        else
           throw new EntityNotFoundException("Produto não cadastrado");
    }


     public Product save(Product product)
    {
         return repository.save(product);
    }

    public void update(Product product, Long id)
    {

      //Se existir projeto com o id retorno ele, se não lança uma exceção de EntityNotFound (é um if else)
        Product p  = repository.findById(id)
                               .orElseThrow(() -> new EntityNotFoundException("Produto não cadastrado"));

        // p = produto que veio do banco - product = produto que vei da requisição http
        
        p.setDescription(product.getDescription());                                
        p.setName(product.getName());
        p.setPrice(product.getPrice());

        repository.save(p);
    }



}
