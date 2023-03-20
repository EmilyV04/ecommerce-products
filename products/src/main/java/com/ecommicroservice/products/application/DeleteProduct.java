package com.ecommicroservice.products.application;

import com.ecommicroservice.products.infrastructure.adapter.DeleteProductAdapter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class DeleteProduct {

  private final DeleteProductAdapter deleteProductAdapter;

  public void deleteProduct(String id) {
    deleteProductAdapter.delete(id);
  }
}
