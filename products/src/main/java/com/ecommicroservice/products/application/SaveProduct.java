package com.ecommicroservice.products.application;

import com.ecommicroservice.products.domain.entities.Product;
import com.ecommicroservice.products.domain.service.SaveService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class SaveProduct {

  private final SaveService<Product> productSaveService;

  public Product save(Product product) {
    return productSaveService.save(product);
  }
}
