package com.products.products.controllers;

import com.products.products.dtos.ProductDTO;
import com.products.products.models.ProductModel;
import com.products.products.repository.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @PostMapping
    public ResponseEntity<ProductModel> registerProduct(@RequestBody ProductDTO productDTO) {
        var product = new ProductModel();
        BeanUtils.copyProperties(productDTO, product);
        return ResponseEntity.status(HttpStatus.CREATED).body(productRepository.save(product));
    }

    @GetMapping
    public ResponseEntity<List<ProductModel>> getAllProducts() {
        return ResponseEntity.status(HttpStatus.OK).body(productRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneProduct(@PathVariable(value = "id") UUID id) {
        Optional<ProductModel> product = productRepository.findById(id);
        return product.<ResponseEntity<Object>>map(productModel -> ResponseEntity.status(HttpStatus.OK).body(productModel)).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found."));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable(value = "id") UUID id, @RequestBody ProductDTO productDTO) {
        Optional<ProductModel> product = productRepository.findById(id);

        if (product.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");

        var productModel = product.get();
        BeanUtils.copyProperties(productDTO, productModel);
        return ResponseEntity.status(HttpStatus.OK).body(productRepository.save(productModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable (value = "id") UUID id){
        Optional<ProductModel> product = productRepository.findById(id);

        if (product.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not Found");

        return ResponseEntity.status(HttpStatus.OK).body("Product deleted successfully");
    }


}
