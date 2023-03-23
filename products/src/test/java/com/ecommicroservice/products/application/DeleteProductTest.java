package com.ecommicroservice.products.application;

import static org.mockito.Mockito.verify;

import com.ecommicroservice.products.infrastructure.adapter.DeleteProductAdapter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DeleteProductTest {

  @Mock
  private DeleteProductAdapter deleteProductAdapter;

  @InjectMocks
  private DeleteProduct deleteProduct;


  @Test
  void deleteProductShouldCallDeleteAdapterWhenGivenId() {
    //given
    String id = "123";

    //when
    deleteProduct.deleteProduct(id);

    //then
    verify(deleteProductAdapter).delete(id);
  }
}