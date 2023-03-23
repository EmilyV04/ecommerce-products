package com.ecommicroservice.products.application;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import com.ecommicroservice.products.domain.entities.Product;
import com.ecommicroservice.products.domain.service.SaveService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SaveProductTest {

  @Mock
  private SaveService<Product> productSaveService;

  @InjectMocks
  private SaveProduct saveProduct;

  @Test
  void itShouldSaveGivenAProduct() {
    //given
    Product product = new Product("1", "p1", 1, 1.0);

    //when
    when(productSaveService.save(product)).thenReturn(product);

    //then
    Product savedProduct = saveProduct.save(product);
    Mockito.verify(productSaveService, times(1)).save(product);
    Assertions.assertThat(savedProduct).isEqualTo(product);
  }
}