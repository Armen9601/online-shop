package com.example.onlineshop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PurchaseDto {

    private long id;
    private UserDto user;
    private long productId;
    private LocalDateTime purchaseDate;
    private double purchasePrice;
    private int quantity;

}
