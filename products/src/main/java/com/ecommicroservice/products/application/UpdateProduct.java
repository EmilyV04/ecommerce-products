package com.ecommicroservice.products.application;

import com.ecommicroservice.products.domain.entities.Product;
import com.ecommicroservice.products.domain.service.UpdateService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UpdateProduct {

  private final UpdateService<Product> productUpdateService;

  public void update(Product product) {
    productUpdateService.update(product);
  }
}
