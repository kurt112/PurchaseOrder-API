package com.API.PurchaseOrder.repository;

import com.API.PurchaseOrder.entity.Approval;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApprovalRepository extends JpaRepository<Approval, Integer> {

}
