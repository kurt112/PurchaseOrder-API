package com.API.PurchaseOrder.service.serviceImplementation;

import com.API.PurchaseOrder.entity.User;
import com.API.PurchaseOrder.repository.UserRepository;
import com.API.PurchaseOrder.service.PageableParentClass.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UserService implements Services<User> {
    final private UserRepository repo;


    @Autowired
    public UserService(UserRepository repo) {
        this.repo = repo;
    }


    public Page<User> approver(String search, int page, int size){
        Pageable pageable = PageRequest.of(page, size);

        return repo.approveruser(search.trim().isEmpty()?"":search,pageable);
    }

    @Override
    public Page<User> data( String search, int page, int size, String OrderBy, boolean order) {
        Pageable pageable = PageRequest.of(page, size);
        search = search.trim().isEmpty()?"":search;
        switch (OrderBy){
            case "status":
                return order?repo.userOrderByStatusAsc(search, pageable):repo.userOrderByStatusDesc(search, pageable);
            case "role":
                return order?repo.userOrderByRoleASC(search, pageable):repo.userOrderByRoleDesc(search, pageable);
            default:
                return order?repo.userOrderByIdASC(search.trim().isEmpty()?"":search, pageable): repo.userOrderByIdDesc(search.trim().isEmpty()?"":search, pageable);
        }

    }

    @Override
    public User save(User user) {
        try {
            repo.save(user);
        } catch (Exception e) {

            return null;
        }
        return user;
    }

    @Override
    public boolean deleteById(int id) {
        try {
            repo.deleteById(id);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public User findById( int id) {
        Optional<User> user = repo.findById(id);
        return user.orElse(null);
    }


    public User findByEmail(String email) {
        return repo.findByEmail(email);
    }

    private Sort orderByIdAsc() {
        return Sort.by((Sort.Direction.ASC),"id");
    }

}
