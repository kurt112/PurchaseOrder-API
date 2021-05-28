package com.API.PurchaseOrder.repository;

import com.API.PurchaseOrder.entity.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Integer> {
}
