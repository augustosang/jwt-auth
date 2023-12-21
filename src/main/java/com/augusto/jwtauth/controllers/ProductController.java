package com.augusto.jwtauth.controllers;

import com.augusto.jwtauth.domain.product.Product;
import com.augusto.jwtauth.domain.product.ProductRequestDto;
import com.augusto.jwtauth.domain.product.ProductResponseDto;
import com.augusto.jwtauth.repositories.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/product")
public class ProductController {

  @Autowired
  ProductRepository productRepository;

  @PostMapping
  public ResponseEntity saveProduct(@RequestBody @Valid ProductRequestDto product) {
    Product newProduct = new Product(product);
    return ResponseEntity.status(HttpStatus.CREATED).body(this.productRepository.save(newProduct));
  }

  @GetMapping
  public ResponseEntity getAllProducts() {
    List<ProductResponseDto> productList = this.productRepository.findAll().stream().map(ProductResponseDto::new).toList();
    return ResponseEntity.ok(productList);
  }
}
