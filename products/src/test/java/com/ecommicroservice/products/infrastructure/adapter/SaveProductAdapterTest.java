package com.ecommicroservice.products.infrastructure.adapter;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
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
class SaveProductAdapterTest {

  @Mock
  private ProductRepository productRepository;
  @Mock
  private ProductMapper productMapper;
  @InjectMocks
  private SaveProductAdapter saveProductAdapter;

  @Test
  void shouldSaveProduct() {
    Product productToSave = new Product("1", "p1", 1, 1.0);

    ProductDto savedProductDto = new ProductDto("1", "p1", 20, 1);
    when(productMapper.toDto(productToSave)).thenReturn(savedProductDto);

    Product savedProduct = new Product("1", "p1", 1, 1.0);
    when(productMapper.toEntity(savedProductDto)).thenReturn(savedProduct);

    when(productRepository.save(savedProductDto)).thenReturn(savedProductDto);

    Product actualSavedProduct = saveProductAdapter.save(productToSave);

    assertThat(actualSavedProduct, equalTo(savedProduct));
  }
}