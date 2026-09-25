package com.fatec.itu.product.services;

import java.util.List;

import org.springframework.stereotype.Service;
import com.fatec.itu.product.entities.Client;
import com.fatec.itu.product.repositories.ClientRepository;

import jakarta.persistence.EntityNotFoundException;

@Service 
public class ClientService {

  private final ClientRepository repository;

    ClientService(ClientRepository repository) {
        this.repository = repository;
    }

    public List<Client> findAll(){
        return repository.findAll();
    }

    public Client findById(Long id) {
        return repository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException());
    }

     public void deleteById(long id) {
      if(repository.existsById(id))
            repository.deleteById(id);
        else
           throw new EntityNotFoundException("Cliente não cadastrado");
    }


     public Client save(Client client)
    {
         return repository.save(client);
    }

    public void update(Client client, Long id)
    {

        Client c  = repository.findById(id)
                               .orElseThrow(() -> new EntityNotFoundException("Cliente não cadastrado"));

        
        c.setName(client.getName());                                
        c.setCity(client.getCity());
        c.setGender(client.getGender());
        c.setAge(client.getAge());


        repository.save(c); 
    }
}
 