package com.API.PurchaseOrder.repository;

import com.API.PurchaseOrder.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query(value = "SELECT t from Product t where t.status =1")
    Page<Product> data(Pageable pageable);
}
