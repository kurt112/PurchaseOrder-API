package com.API.PurchaseOrder.repository;

import com.API.PurchaseOrder.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query(value = "SELECT t from Order t where t.status = ?1")
    Page<Order> getOrder(int status, Pageable pageable);
}
