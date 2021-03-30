package com.API.PurchaseOrder.repository;

import com.API.PurchaseOrder.entity.Sector;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SectorRepository extends JpaRepository<Sector, Integer> {

    @Query(value = "SELECT t from Sector t")

//    "t.status = ?1 or " +
//            "t.approver =?1 or " +
//            "t.sub_approver =?1 or " +
//            "t.createAt =?1 or " +
//            "t.updateAt =?1 or " +
//            "t.deleteAt =?1 "
    Page<Sector> data(String search, Pageable pageable);
}
