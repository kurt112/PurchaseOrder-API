package com.API.PurchaseOrder.repository;

import com.API.PurchaseOrder.entity.Sector;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SectorRepository extends JpaRepository<Sector, Integer> {

    @Query(value = "SELECT t from Sector t where t.name like %?1%")
    Page<Sector> sectors(String search, Pageable pageable);
}
