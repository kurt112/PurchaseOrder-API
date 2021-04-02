package com.API.PurchaseOrder.repository;

import com.API.PurchaseOrder.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "Select t from User t where t.email=?1")
    User findByEmail(String email);


    @Query(value = "Select t from User t where t.firstName like %?1%")
    Page<User> users(String Search, Pageable pageable);
}
