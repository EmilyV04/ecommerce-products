package com.ecommicroservice.products.infrastructure.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "products")
public class ProductDto {

  @Id
  private String id;
  private String name;
  private String description;
  private double price;
  private String category;
  private int stock;

}
