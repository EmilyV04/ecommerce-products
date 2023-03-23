package com.ecommicroservice.products.application;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.ecommicroservice.products.domain.entities.Product;
import com.ecommicroservice.products.domain.service.UpdateService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UpdateProductTest {

  @Mock
  private UpdateService<Product> productUpdateService;

  @Test
  void shouldUpdateGivenAProduct() {
    //given
    Product product = new Product("1", "p1", 1, 1.0);

    //when
    productUpdateService.update(product);

    //then
    verify(productUpdateService, times(1)).update(product);
  }
}