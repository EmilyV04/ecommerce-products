package com.ecommicroservice.products.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product {

  private String id;
  private String name;
  private int stock;
  private double price;

}
