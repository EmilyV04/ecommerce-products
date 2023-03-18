package com.ecommicroservice.products.domain.service;

import java.util.List;

public interface GetService<E> {

  List<E> getAll();

  E getById(String id);

}
