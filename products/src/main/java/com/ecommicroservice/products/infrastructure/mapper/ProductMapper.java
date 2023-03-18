package com.ecommicroservice.products.infrastructure.mapper;

import com.ecommicroservice.products.domain.entities.Product;
import com.ecommicroservice.products.infrastructure.dto.ProductDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

  Product toEntity(ProductDto productDto);

  ProductDto toDto(Product product);

}
