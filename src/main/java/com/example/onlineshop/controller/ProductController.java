package com.example.onlineshop.controller;

import com.example.onlineshop.dto.ProductDto;
import com.example.onlineshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductDto> addProduct(HttpServletRequest request, @RequestBody ProductDto productDto) {
        String token = request.getHeader("Authorization");
        System.out.println(token);
        ProductDto addedProduct = productService.add(productDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long id, @RequestBody ProductDto productDto) {
        productDto.setId(id);
        ProductDto updatedProduct = productService.update(productDto);
        return ResponseEntity.ok(updatedProduct);
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<ProductDto> products = productService.findAll();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id) {
        ProductDto product = productService.findById(id);
        return ResponseEntity.ok(product);
    }

    @PostMapping("/{id}/buy")
    public ResponseEntity<Void> buyProduct(HttpServletRequest request, @PathVariable Long id) {
        productService.buyProduct(request,id);
        return ResponseEntity.noContent().build();
    }
}
