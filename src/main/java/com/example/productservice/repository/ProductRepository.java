package com.example.productservice.repository;

import com.example.productservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

    //Product = Entity class we want to work with.
    //Long = Data type of the primary key (id).

   /* Spring Data JPA is that we don't have to write SQL queries for basic CRUD operations.
By extending JpaRepository, we automatically get methods like:
    save()	Insert or update product
    findAll()	Get all products
    findById()	Get product by ID
    deleteById()	Delete product by ID */
}
