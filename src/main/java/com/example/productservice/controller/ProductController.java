package com.example.productservice.controller;

import com.example.productservice.entity.Product;
import com.example.productservice.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//Makes this class a REST API controller
@RequestMapping("/api/products")
//Defines the common URL
public class ProductController {
//controller depends on the ProductService interface, not directly on ProductServiceImpl.
// Spring injects the implementation automatically.
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
//@RequestBody -Converts incoming JSON into a Java object
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product saveProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }
//@PAthvariable-reads the parameter from URL
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PutMapping("/{id}")
    public Product updateProduct(
            @PathVariable Long id,
            @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }

    @GetMapping("/instance")
    public String getInstance(HttpServletRequest request) {
        return "Request handled by Product Service on port: "
                + request.getLocalPort();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}
