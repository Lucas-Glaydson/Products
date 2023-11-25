package com.products.products.controllers;

import com.products.products.dtos.ProductDTO;
import com.products.products.models.ProductModel;
import com.products.products.repository.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @PutMapping
    public ResponseEntity<ProductModel> registerProduct(@RequestBody ProductDTO productDTO){
        var product = new ProductModel();
        BeanUtils.copyProperties(productDTO, product);
        return ResponseEntity.status(HttpStatus.CREATED).body(productRepository.save(product));
    }
}
