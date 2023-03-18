package com.ecommicroservice.products.infrastructure.repository;

import com.ecommicroservice.products.infrastructure.dto.ProductDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductDto, String> {

}
