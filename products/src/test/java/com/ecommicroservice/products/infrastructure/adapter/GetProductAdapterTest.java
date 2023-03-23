package com.ecommicroservice.products.infrastructure.adapter;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.ecommicroservice.products.domain.entities.Product;
import com.ecommicroservice.products.infrastructure.dto.ProductDto;
import com.ecommicroservice.products.infrastructure.mapper.ProductMapper;
import com.ecommicroservice.products.infrastructure.repository.ProductRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetProductAdapterTest {

  @Mock
  private ProductRepository productRepository;
  @Mock
  private ProductMapper productMapper;
  @InjectMocks
  private GetProductAdapter getProductAdapter;

  @Test
  void shouldReturnAllProducts() {
    List<ProductDto> listDto = new ArrayList<>();
    listDto.add(new ProductDto("1", "p1", 10, 1.0));
    listDto.add(new ProductDto("2", "p2", 10, 1.0));

    when(productRepository.findAll()).thenReturn(listDto);

    List<Product> listProductExpected = new ArrayList<>();
    listProductExpected.add(new Product("1", "p1", 10, 1.0));
    listProductExpected.add(new Product("2", "p2", 10, 1.0));

    when(productMapper.toEntity(any(ProductDto.class))).thenAnswer(invocation -> {
      ProductDto dto = invocation.getArgument(0);
      return new Product(dto.getId(), dto.getName(), dto.getStock(), dto.getPrice());
    }); /* This implementation will extract the ProductDto passed as an argument to the toEntity method,
    * and create a new Product with the same values. Then, it will return that Product instance
    * as the result of the method call. With this change, the actual list of products returned
    * by getProductAdapter.getAll() should have the same values as the expected list,
    * and the assertion should pass.*/

    List<Product> listProductActual = getProductAdapter.getAll();

    assertThat(listProductActual, equalTo(listProductExpected));
  }

  @Test
  void getById() {
    //given
    String id = "1";
    ProductDto productDto = new ProductDto("1", "p1", 1, 1.0);

    when(productRepository.findById(id)).thenReturn(Optional.of(productDto));

    //when
    when(productMapper.toEntity(productDto)).thenReturn(new Product("1", "p1", 1, 1.0));

    Product productExpected = new Product("1", "p1", 1, 1.0);
    Product productActual = getProductAdapter.getById(id);

    //then
    assertThat(productActual, equalTo(productExpected));
  }
}