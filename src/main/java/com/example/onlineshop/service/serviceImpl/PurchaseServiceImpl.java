package com.example.onlineshop.service.serviceImpl;

import com.example.onlineshop.dto.PurchaseDto;
import com.example.onlineshop.entity.Purchase;
import com.example.onlineshop.mapper.PurchaseMapper;
import com.example.onlineshop.repo.PurchaseRepository;
import com.example.onlineshop.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class PurchaseServiceImpl implements PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final PurchaseMapper purchaseMapper;

    @Override
    public PurchaseDto add(PurchaseDto purchaseDto) {
        Purchase purchase = purchaseMapper.toEntity(purchaseDto);
        Purchase saved = purchaseRepository.save(purchase);
        log.info("purchase saved: id " + saved.getId());
        return purchaseMapper.toDto(saved);
    }
}
