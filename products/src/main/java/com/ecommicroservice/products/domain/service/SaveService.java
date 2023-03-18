package com.ecommicroservice.products.domain.service;

public interface SaveService<E> {

  E save(E itemToSave);

}
