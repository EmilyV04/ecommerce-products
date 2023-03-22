package com.ecommicroservice.products.infrastructure.event.listen;

import com.ecommicroservice.products.infrastructure.dto.OrderMessageDto;
import com.ecommicroservice.products.infrastructure.dto.ProductDto;
import com.ecommicroservice.products.infrastructure.repository.ProductRepository;
import com.ecommicroservice.products.shared.exception.ProductNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class OrderConsumer {

  private final ProductRepository productRepository;

  @RabbitListener(queues = "orders-queue")
  public void receiveMessage(OrderMessageDto message) {
    // Get product by ID
    ProductDto product = productRepository.findById(message.getProductId())
        .orElseThrow(() -> new ProductNotFoundException("Product not found"));

    // Update product quantity
    product.setStock(product.getStock() - message.getQuantity());
    productRepository.save(product);
  }


}
