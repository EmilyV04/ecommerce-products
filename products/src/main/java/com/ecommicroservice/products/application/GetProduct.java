package com.ecommicroservice.products.application;

import com.ecommicroservice.products.domain.entities.Product;
import com.ecommicroservice.products.domain.service.GetService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class GetProduct {

  private final GetService<Product> productGetService;

  public List<Product> getAllProducts() {
    return productGetService.getAll();
  }

  public Product getProduct(String id) {
    return productGetService.getById(id);
  }
}
