package com.fatec.itu.product.dtos;

public record ClientRequest(
    String name,
    int age,
    String city,
    String gender
) {
    
}
