package com.ecommicroservice.products.infrastructure.adapter;

import com.ecommicroservice.products.domain.service.DeleteService;
import com.ecommicroservice.products.infrastructure.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class DeleteProductAdapter implements DeleteService {

  private final ProductRepository productRepository;

  @Override
  public void delete(String id) {
    productRepository.deleteById(id);
  }
}
