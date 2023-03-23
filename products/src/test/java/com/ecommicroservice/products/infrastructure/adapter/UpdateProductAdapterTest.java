package com.ecommicroservice.products.infrastructure.adapter;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.ecommicroservice.products.domain.entities.Product;
import com.ecommicroservice.products.infrastructure.dto.ProductDto;
import com.ecommicroservice.products.infrastructure.mapper.ProductMapper;
import com.ecommicroservice.products.infrastructure.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UpdateProductAdapterTest {

  @Mock
  private ProductRepository productRepository;
  @Mock
  private ProductMapper productMapper;
  @InjectMocks
  private UpdateProductAdapter updateProductAdapter;

  @Test
  void shouldUpdateProduct() {
    Product productToUpdate = new Product("1", "p1", 1, 1.0);
    ProductDto dto = new ProductDto("1", "p1", 1, 1.0);

    when(productMapper.toDto(productToUpdate)).thenReturn(dto);

    updateProductAdapter.update(productToUpdate);

    verify(productMapper).toDto(productToUpdate);
    verify(productRepository).save(dto);
  }
}