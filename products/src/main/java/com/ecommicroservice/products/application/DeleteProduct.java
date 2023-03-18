package com.ecommicroservice.products.application;

import com.ecommicroservice.products.infrastructure.adapter.DeleteProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class DeleteProduct {

  private final DeleteProductService deleteProductService;

  public void deleteProduct(String id) {
    deleteProductService.delete(id);
  }
}
