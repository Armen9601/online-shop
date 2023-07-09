package com.example.onlineshop.mapper;

import com.example.onlineshop.dto.ProductDto;
import com.example.onlineshop.entity.Product;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface ProductMapper extends EntityMapper<ProductDto, Product> {
}
