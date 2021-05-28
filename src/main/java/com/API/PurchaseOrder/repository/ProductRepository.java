package com.API.PurchaseOrder.repository;

import com.API.PurchaseOrder.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product, Integer> {
}
