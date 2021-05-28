package com.API.PurchaseOrder.repository;

import com.API.PurchaseOrder.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {

}
