package com.ecommicroservice.products.application;

import static org.mockito.Mockito.when;

import com.ecommicroservice.products.domain.entities.Product;
import com.ecommicroservice.products.domain.service.GetService;
import java.util.Arrays;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetProductTest {

  @Mock
  private GetService<Product> productGetService;

  @InjectMocks
  private GetProduct getProduct;

  @Test
  void getAllProductsShouldReturnProductList() {
    //given
    List<Product> expectedProducts = Arrays.asList(
        new Product("1", "p1", 1, 1.0),
        new Product("2", "p2", 1, 1.0),
        new Product("3", "p3", 1, 1.0)
    );
    when(productGetService.getAll()).thenReturn(expectedProducts);

    //when
    List<Product> actualProducts = getProduct.getAllProducts();

    //then
    Assertions.assertThat(expectedProducts).isEqualTo(actualProducts);
  }

  @Test
  void getProductShouldReturnAProductGivenTheId() {
    //given
    String id = "123";
    Product expectedProduct = new Product("123","product",1,1.0);
    //when
    when(productGetService.getById(id)).thenReturn(expectedProduct);
    //then
    Product actualProduct = getProduct.getProduct(id);
    Assertions.assertThat(actualProduct).isEqualTo(expectedProduct);
  }
}