package com.API.PurchaseOrder.repository;

import com.API.PurchaseOrder.entity.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Integer> {
}
