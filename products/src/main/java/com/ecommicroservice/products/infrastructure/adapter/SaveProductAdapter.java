package com.ecommicroservice.products.infrastructure.adapter;

import com.ecommicroservice.products.domain.entities.Product;
import com.ecommicroservice.products.domain.service.SaveService;
import com.ecommicroservice.products.infrastructure.mapper.ProductMapper;
import com.ecommicroservice.products.infrastructure.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class SaveProductAdapter implements SaveService<Product> {

  private final ProductRepository productRepository;
  private final ProductMapper productMapper;

  @Override
  public Product save(Product itemToSave) {
    return productMapper.toEntity(productRepository.save(productMapper.toDto(itemToSave)));
  }
}
