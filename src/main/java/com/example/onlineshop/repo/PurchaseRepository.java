package com.example.onlineshop.repo;

import com.example.onlineshop.entity.Product;
import com.example.onlineshop.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
}
