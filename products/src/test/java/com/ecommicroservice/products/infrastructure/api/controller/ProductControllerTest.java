package com.ecommicroservice.products.infrastructure.api.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.ecommicroservice.products.application.DeleteProduct;
import com.ecommicroservice.products.application.GetProduct;
import com.ecommicroservice.products.application.SaveProduct;
import com.ecommicroservice.products.application.UpdateProduct;
import com.ecommicroservice.products.domain.entities.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(ProductController.class)
class ProductControllerTest {

  @Autowired
  private MockMvc mockMvc;
  @MockBean
  private GetProduct getProduct;
  @MockBean
  private SaveProduct saveProduct;
  @MockBean
  private UpdateProduct updateProduct;
  @MockBean
  private DeleteProduct deleteProduct;
  @Autowired
  private ObjectMapper objectMapper;

  @Test
  void shouldReturnAllProducts() throws Exception {
    List<Product> productList = new ArrayList<>();
    productList.add(new Product("1", "p1", 1, 20.0));
    productList.add(new Product("2", "p2", 1, 20.0));

    when(getProduct.getAllProducts()).thenReturn(productList);

    RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/products")
        .accept(MediaType.APPLICATION_JSON);
    MvcResult result = mockMvc.perform(requestBuilder).andReturn();
    MockHttpServletResponse response = result.getResponse();

    assertEquals(HttpStatus.OK.value(), response.getStatus());
  }

  @Test
  void shouldReturnOrderById() throws Exception {
    Product product = new Product("1", "p1", 1, 20.0);

    when(getProduct.getProduct("1")).thenReturn(product);

    RequestBuilder requestBuilder = MockMvcRequestBuilders
        .get("/api/products/{id}", "1")
        .accept(MediaType.APPLICATION_JSON);

    MvcResult result = mockMvc.perform(requestBuilder).andReturn();
    MockHttpServletResponse response = result.getResponse();

    Product productResponse = objectMapper.readValue(response.getContentAsString(), Product.class);

    assertEquals(productResponse, product);
    assertEquals(HttpStatus.OK.value(), response.getStatus());
  }

  @Test
  void shouldSaveAGivenProduct() throws Exception {
    Product product = new Product("1", "p1", 1, 20.0);

    String requestBody = new ObjectMapper().writeValueAsString(product);

    when(saveProduct.save(any(Product.class))).thenReturn(product);

    mockMvc.perform(post("/api/products")
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestBody))
        .andExpect(status().isOk())
        .andExpect(jsonPath("id", equalTo("1")))
        .andExpect(jsonPath("name", equalTo("p1")))
        .andExpect(jsonPath("stock", equalTo(1)))
        .andExpect(jsonPath("price", equalTo(20.0)));

    Mockito.verify(saveProduct).save(any(Product.class));
  }

  @Test
  void updateProduct() throws Exception {
    Product product = new Product("1", "p1", 1, 20.0);

    String requestBody = objectMapper.writeValueAsString(product);

    RequestBuilder requestBuilder = MockMvcRequestBuilders
        .put("/api/products/{id}", "1")
        .contentType(MediaType.APPLICATION_JSON)
        .content(requestBody);

    mockMvc.perform(requestBuilder)
        .andExpect(status().isOk());

    Mockito.verify(updateProduct).update(any(Product.class));
  }

  @Test
  void deleteProduct() throws Exception {
    doAnswer(invocation -> null).when(deleteProduct).deleteProduct("1");

    RequestBuilder requestBuilder = MockMvcRequestBuilders
        .delete("/api/products/{id}", "1");

    mockMvc.perform(requestBuilder)
        .andExpect(status().isOk());

    Mockito.verify(deleteProduct).deleteProduct("1");
  }
}