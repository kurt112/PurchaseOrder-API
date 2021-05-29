package com.API.PurchaseOrder.repository;

import com.API.PurchaseOrder.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query(value = "SELECT t from Order t where t.status = ?1")
    Page<Order> getOrder(int status, Pageable pageable);

    @Query(value = "SELECT t from Order t where t.status = ?1 and t.requestor.id =?2")
    Page<Order> getOrderWithRequestor(int status, int requestorId, Pageable pageable);

    @Query(value = "SELECT t from Order t where t.status = ?1 and t.approval.id =?2")
    Page<Order> getOrderWithApproval(int status,  int approvalId, Pageable pageable);

    @Query(value = "SELECT t from Order t where t.status = ?1 and t.requestor.id =?2 and t.approval.id =?3")
    Page<Order> getOrderWithRequestorAndApproval(int status, int requestorId,int approvalId, Pageable pageable);
}
