package com.API.PurchaseOrder.repository;

import com.API.PurchaseOrder.entity.Approval;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ApprovalRepository extends JpaRepository<Approval, Integer> {

    @Query(value = "SELECT t FROM Approval t where t.status =1")
    Page<Approval> getApproval(int status,Pageable pageable);
}
