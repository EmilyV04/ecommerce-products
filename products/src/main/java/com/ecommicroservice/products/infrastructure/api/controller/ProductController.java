package com.ecommicroservice.products.infrastructure.api.controller;

import com.ecommicroservice.products.application.DeleteProduct;
import com.ecommicroservice.products.application.GetProduct;
import com.ecommicroservice.products.application.SaveProduct;
import com.ecommicroservice.products.application.UpdateProduct;
import com.ecommicroservice.products.domain.entities.Product;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/products")
public class ProductController {

  private final GetProduct getProduct;
  private final SaveProduct saveProduct;
  private final UpdateProduct updateProduct;
  private final DeleteProduct deleteProduct;

  @GetMapping
  public ResponseEntity<List<Product>> getAllProducts() {
    return ResponseEntity.ok(getProduct.getAllProducts());
  }

  @GetMapping(path = "{id}")
  public ResponseEntity<Product> getProduct(@PathVariable String id) {
    return ResponseEntity.ok(getProduct.getProduct(id));
  }

  @PostMapping
  public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
    return ResponseEntity.ok(saveProduct.save(product));
  }

  @PutMapping(path = "{id}")
  public void updateProduct(@RequestBody Product product) {
    updateProduct.update(product);
  }

  @DeleteMapping(path = "{id}")
  public void deleteProduct(@PathVariable String id) {
    deleteProduct.deleteProduct(id);
  }
}
