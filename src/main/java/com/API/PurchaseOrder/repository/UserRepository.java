package com.API.PurchaseOrder.repository;

import com.API.PurchaseOrder.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Sort;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "Select t from User t where t.email=?1")
    User findByEmail(String email);


    @Query(value = "Select t from User t where t.firstName like %?1% order by t.id DESC")
    Page<User> userOrderByIdDesc(String Search, Pageable pageable);

    @Query(value = "Select t from User t where t.firstName like %?1% order by t.id ASC")
    Page<User> userOrderByIdASC(String Search, Pageable pageable);

    @Query(value = "Select t from User t where t.firstName like %?1% order by t.role ASC")
    Page<User> userOrderByRoleASC(String Search, Pageable pageable);

    @Query(value = "Select t from User t where t.firstName like %?1% order by t.role Desc")
    Page<User> userOrderByRoleDesc(String Search, Pageable pageable);



    @Query(value = "Select t from User t where t.firstName like %?1% order by t.status ASC")
    Page<User> userOrderByStatusAsc(String Search, Pageable pageable);

    @Query(value = "Select t from User t where t.firstName like %?1% order by t.status DESC")
    Page<User> userOrderByStatusDesc(String Search, Pageable pageable);



    @Query(value = "select t from User t where t.role =3 or t.firstName like %?1%")
    Page<User> approveruser(String search, Pageable pageable);

}
