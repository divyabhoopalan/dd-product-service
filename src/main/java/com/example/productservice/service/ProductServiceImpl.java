package com.example.productservice.service;


import com.example.productservice.entity.Product;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.example.productservice.repository.ProductRepository;

import java.util.List;

//@Service tells Spring that this is a service-layer component.
@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    //This is constructor dependency injection.
    // Spring automatically provides the ProductRepository object when creating ProductServiceImpl
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
//@Override indicates that we are implementing a method declared in the interface.
    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Product not found"
                        ));
    }

    @Override
    public Product updateProduct(Long id, Product product) {

        Product existingProduct = getProductById(id);

        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());

        return productRepository.save(existingProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        Product existingProduct = getProductById(id);
        productRepository.delete(existingProduct);

    }
}



