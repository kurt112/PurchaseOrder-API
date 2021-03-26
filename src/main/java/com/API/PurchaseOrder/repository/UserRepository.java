package com.API.PurchaseOrder.repository;

import com.API.PurchaseOrder.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(name = "Select t from User t where t.email=?1")
    User findByEmail(String email);
}
