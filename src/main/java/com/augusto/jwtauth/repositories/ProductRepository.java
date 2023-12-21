package com.augusto.jwtauth.repositories;

import com.augusto.jwtauth.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
}
