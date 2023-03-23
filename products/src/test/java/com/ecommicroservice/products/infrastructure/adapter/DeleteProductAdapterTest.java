package com.ecommicroservice.products.infrastructure.adapter;

import static org.mockito.Mockito.verify;

import com.ecommicroservice.products.infrastructure.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DeleteProductAdapterTest {

  @Mock
  private ProductRepository productRepository;

  @InjectMocks
  private DeleteProductAdapter deleteProductAdapter;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
    deleteProductAdapter = new DeleteProductAdapter(productRepository);
  }

  @Test
  void shouldDeleteGivenId() {
    String id = "123";
    deleteProductAdapter.delete(id);
    verify(productRepository).deleteById(id);
  }
}