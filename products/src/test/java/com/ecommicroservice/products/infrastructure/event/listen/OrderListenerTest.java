package com.ecommicroservice.products.infrastructure.event.listen;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import com.ecommicroservice.products.infrastructure.dto.OrderMessageDto;
import com.ecommicroservice.products.infrastructure.dto.ProductDto;
import com.ecommicroservice.products.infrastructure.repository.ProductRepository;
import com.ecommicroservice.products.shared.exception.ProductNotFoundException;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class OrderListenerTest {
  @Mock
  private ProductRepository productRepository;

  @InjectMocks
  private OrderListener orderListener;

  @Test
  void receiveMessage_shouldUpdateProductQuantity() {
    // Given
    OrderMessageDto message = new OrderMessageDto("123", 2);
    ProductDto product = new ProductDto("123", "Product 1", 5, 10.0);
    when(productRepository.findById("123")).thenReturn(Optional.of(product));

    // When
    orderListener.receiveMessage(message);

    // Then
    Mockito.verify(productRepository).findById("123");
    Mockito.verify(productRepository).save(argThat(updatedProduct -> updatedProduct.getStock() == 3));
  }

  @Test
  void receiveMessage_shouldThrowProductNotFoundException() {
    // Given
    OrderMessageDto message = new OrderMessageDto("123", 2);
    when(productRepository.findById("123")).thenReturn(Optional.empty());

    // When, Then
    assertThrows(ProductNotFoundException.class, () -> orderListener.receiveMessage(message));

    Mockito.verify(productRepository).findById("123");
    verifyNoMoreInteractions(productRepository);
  }
}