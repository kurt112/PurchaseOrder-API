package com.API.PurchaseOrder.repository;

import com.API.PurchaseOrder.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
}
