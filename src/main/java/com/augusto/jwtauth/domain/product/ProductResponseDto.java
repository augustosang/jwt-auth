package com.augusto.jwtauth.domain.product;

import java.util.UUID;

public record ProductResponseDto(UUID id, String name, Integer price) {
  public ProductResponseDto(Product product){
    this(product.getId(), product.getName(), product.getPrice());
  }
}
