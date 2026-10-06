package com.fatec.itu.product.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatec.itu.product.dtos.ProductRequest;
import com.fatec.itu.product.dtos.ProductResponse;
import com.fatec.itu.product.entities.Product;
import com.fatec.itu.product.mappers.ProductMapper;
import com.fatec.itu.product.repositories.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
 
@Service
public class ProductService {
  
  @Autowired
  private ProductRepository repository;


    

    // Primeiro service - pesquisa produto pelo id
     public ProductResponse findById(Long id) {
        return repository.findById(id) // Devolve UM OBJETO PRODUTO
                         .map(ProductMapper::toDTO)
                         .orElseThrow(() -> new EntityNotFoundException()); // trata exceção/erro
    }

    // Lista de todos os produtos
    public List<ProductResponse> findAll(){
      return repository.findAll()
                       .stream()
                       .map(ProductMapper::toDTO)
                       .toList();

    }


    public void deleteById(long id) {
      //Primeiro verifica se existe algum produto com o id, se existir deleta se não retorna uma mensagem de erro
      if(repository.existsById(id))
            repository.deleteById(id);
        else
           throw new EntityNotFoundException("Produto não cadastrado");
    }


     public ProductResponse save(ProductRequest product)
    {
        Product p = repository.save(ProductMapper.toEntity(product));
         return ProductMapper.toDTO(p);
    }

    public void update(ProductRequest product, Long id)
    {

      //Se existir projeto com o id retorno ele, se não lança uma exceção de EntityNotFound (é um if else)
        Product p  = repository.findById(id)
                               .orElseThrow(() -> new EntityNotFoundException("Produto não cadastrado"));

        // p = produto que veio do banco - product = produto que vei da requisição http
        
        p.setDescription(product.description());                                
        p.setName(product.name());
        p.setPrice(product.price());

        repository.save(p);
    }



}
