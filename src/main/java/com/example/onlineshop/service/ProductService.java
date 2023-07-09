package com.example.onlineshop.service;

import com.example.onlineshop.dto.ProductDto;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ProductService {

    ProductDto add(ProductDto productDto);

    void delete(Long id);

    ProductDto update(ProductDto productDto);

    List<ProductDto> findAll();

    ProductDto findById(Long id);

    void buyProduct(HttpServletRequest request, Long id);

}
