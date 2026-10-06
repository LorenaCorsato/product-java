package com.fatec.itu.product.dtos;

public record ClientResponse(
    Long id,
    String name,
    int age,
    String city,
    String gender
) {

    
}
