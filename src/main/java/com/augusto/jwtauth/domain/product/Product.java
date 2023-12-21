package com.augusto.jwtauth.domain.product;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Table(name = "product")
@Entity(name = "product")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  private String name;

  private Integer price;

  public Product(ProductRequestDto productRequestDto) {
    this.name = productRequestDto.name();
    this.price = productRequestDto.price();
  }
}
