package com.API.PurchaseOrder.repository;

import com.API.PurchaseOrder.entity.SubSector;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SubSectorRepository extends JpaRepository<SubSector, Integer> {

    @Query(value = "SELECT t from SubSector t where t.name like %?1%")
    Page<SubSector> data(String search, Pageable pageable);
}
