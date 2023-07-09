package com.example.onlineshop.service.serviceImpl;

import com.example.onlineshop.dto.ProductDto;
import com.example.onlineshop.dto.PurchaseDto;
import com.example.onlineshop.dto.UserDto;
import com.example.onlineshop.entity.Product;
import com.example.onlineshop.mapper.ProductMapper;
import com.example.onlineshop.repo.ProductRepository;
import com.example.onlineshop.service.ProductService;
import com.example.onlineshop.service.PurchaseService;
import com.example.onlineshop.service.UserService;
import com.example.onlineshop.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
@Log4j2
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final PurchaseService purchaseService;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserService userService;

    @Override
    public ProductDto add(ProductDto productDto) {
        Product product = productMapper.toEntity(productDto);
        Product saved = productRepository.save(product);
        log.info("Adding a product:" + productDto.getName());
        return productMapper.toDto(saved);
    }

    @Override
    public void delete(Long id) {
        Product product = productRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        productRepository.delete(product);
        log.info("Deleting a product with ID: {}", id);
    }

    @Override
    public ProductDto update(ProductDto productDto) {
        Product product = productMapper.toEntity(productDto);
        Product saved = productRepository.save(product);
        log.info("Updating a product: {}", productDto.getName());
        return productMapper.toDto(saved);
    }

    @Override
    public List<ProductDto> findAll() {
        log.info("Fetching all products");
        return productMapper.toDto(productRepository.findAll());
    }

    @Override
    public ProductDto findById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        log.info("Fetching a product by ID: " + id);
        return productMapper.toDto(product);
    }

    @Override
    public void buyProduct(HttpServletRequest request, Long id) {
        String token = request.getHeader("Authorization");
        String usernameFromToken = jwtTokenUtil.getUsernameFromToken(token);
        UserDto userDto = userService.findByUsername(usernameFromToken);
        Product product = productRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        product.setQuantity(product.getQuantity() - 1);
        productRepository.save(product);
        purchaseService.add(PurchaseDto.builder()
                .productId(id)
                .user(userDto)
                .purchaseDate(LocalDateTime.now())
                .quantity(1)
                .purchasePrice(product.getPrice()).build());
        log.info("Processing purchase for product with ID: {}", id);
    }
}
