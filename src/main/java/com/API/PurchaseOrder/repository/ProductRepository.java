package com.API.PurchaseOrder.repository;

import com.API.PurchaseOrder.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query(value = "SELECT t from Product t where t.status =1")
    Page<Product> data(Pageable pageable);

    @Query(value = "SELECT t from Product t where t.supplier = ?2 and t.subSector = ?3 and t.status = ?4")
    Page<Product> getProductBySupplierAAndSubSectorWithStatus(Pageable pageable, int supplierId, int subSectorId, int status);

    @Query(value = "SELECT t from Product t where t.supplier = ?2 and t.subSector = ?3")
    Page<Product> getProductBySupplierAAndSubSector(Pageable pageable, int supplierId, int subSectorId);


    @Query(value = "SELECT t from Product t where t.supplier = ?2 and t.status = ?3")
    Page<Product> getProductBySupplierWithStatus(Pageable pageable, int supplierId, int status);

    @Query(value = "SELECT t from Product t where t.supplier = ?2 ")
    Page<Product> getProductBySupplier(Pageable pageable, int supplierId);

    @Query(value = "SELECT t from Product t where t.supplier = ?2 and t.status = ?3")
    Page<Product> getProductBySubSectorWithStatus(Pageable pageable, int subSectorId, int status);

    @Query(value = "SELECT t from Product t where t.supplier = ?2 ")
    Page<Product> getProductBySubSector(Pageable pageable, int subSectorId);

    @Query(value = "SELECT t from Product t where t.status = ?2 ")
    Page<Product> getProductByStatus(Pageable pageable, int status);


}
