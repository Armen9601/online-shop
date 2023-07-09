package com.example.onlineshop.mapper;

import com.example.onlineshop.dto.PurchaseDto;
import com.example.onlineshop.entity.Purchase;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface PurchaseMapper extends EntityMapper<PurchaseDto, Purchase> {
}
