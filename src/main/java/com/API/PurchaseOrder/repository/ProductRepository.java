package com.API.PurchaseOrder.repository;

import com.API.PurchaseOrder.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query(value = "SELECT t from Product t where t.status =1")
    Page<Product> data(Pageable pageable);

    @Query(value = "SELECT t from Product t where t.supplier.id = ?1 and t.subSector.id = ?2 and t.status = ?3")
    Page<Product> getProductBySupplierAAndSubSectorWithStatus( int supplierId, int subSectorId, int status,Pageable pageable);

    @Query(value = "SELECT t from Product t where t.supplier.id = ?1 and t.subSector.id = ?2")
    Page<Product> getProductBySupplierAAndSubSector(int supplierId, int subSectorId,Pageable pageable);


    @Query(value = "SELECT t from Product t where t.supplier.id = ?1 and t.status = ?2")
    Page<Product> getProductBySupplierWithStatus(int supplierId, int status,Pageable pageable);

    @Query(value = "SELECT t from Product t where t.supplier.id = ?1 ")
    Page<Product> getProductBySupplier(int supplierId,Pageable pageable);

    @Query(value = "SELECT t from Product t where t.subSector.id = ?1 and t.status = ?2")
    Page<Product> getProductBySubSectorWithStatus(int subSectorId, int status,Pageable pageable);

    @Query(value = "SELECT t from Product t where t.subSector.id = ?1 ")
    Page<Product> getProductBySubSector(int subSectorId, Pageable pageable);

    @Query(value = "SELECT t from Product t where t.status = ?1 ")
    Page<Product> getProductByStatus(int status, Pageable pageable);


}
