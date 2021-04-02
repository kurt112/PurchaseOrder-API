package com.API.PurchaseOrder.service.serviceImplementation;

import com.API.PurchaseOrder.entity.API.ApiSettings;
import com.API.PurchaseOrder.entity.User;
import com.API.PurchaseOrder.repository.UserRepository;
import com.API.PurchaseOrder.service.PageableParentClass.ServicesGraphQl;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@GraphQLApi
public class UserService implements ServicesGraphQl<User> {
    final private UserRepository repo;
    private int totalPages = 0;
    private long totalElements = 0;
    private int currentPages = 0;
    @Autowired
    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    @Override
    @GraphQLQuery(name = "getUsers")
    public List<User> data(@GraphQLArgument(name = "search")String search,
                           @GraphQLArgument(name = "page") int page,
                           @GraphQLArgument(name = "size") int size) {
        Pageable pageable = PageRequest.of(page,size);
        Page<User> pages =  repo.findAll(pageable);
        totalElements =  pages.getTotalElements();
        totalPages = pages.getTotalPages();
        currentPages = page;
        System.out.println(pages.getContent());
        return  pages.getContent();// pages.getContent();
    }

    @Override
    @GraphQLQuery(name = "saveUser")
    public User save(User user) {
        try {
            repo.save(user);
        }catch (Exception e){

            return null;
        }
        return user;
    }

    @Override
    public boolean deleteById(int id) {
        try {
            repo.deleteById(id);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    @GraphQLQuery(name = "getUser")
    public User findById(@GraphQLArgument(name = "id") int id) {
        Optional<User> user = repo.findById(id);
        return user.orElse(null);
    }


    public User findByEmail(String email){
        User user = repo.findByEmail(email);
        return user;
    }

    @Override
    public ApiSettings apiSettings() {
        return new ApiSettings(totalElements, totalPages, currentPages);
    }
}
