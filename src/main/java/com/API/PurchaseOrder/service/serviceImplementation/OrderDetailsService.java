package com.API.PurchaseOrder.service.serviceImplementation;

import com.API.PurchaseOrder.entity.Order;
import com.API.PurchaseOrder.entity.OrderDetails;
import com.API.PurchaseOrder.repository.OrderDetailsRepository;
import com.API.PurchaseOrder.service.PageableParentClass.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class OrderDetailsService implements Services<OrderDetails> {

    private final OrderDetailsRepository repo;

    @Autowired
    public OrderDetailsService(OrderDetailsRepository repo) {
        this.repo = repo;
    }

    public Page<OrderDetails> getOrderDetails(String search, int page, int size, int status){
        Pageable pageable = PageRequest.of(page,size);
        return repo.findAll(pageable);
    }

    @Override
    public Page<OrderDetails> data(String search, int page, int size, String orderBy, boolean order) {
        return null;
    }

    @Override
    public OrderDetails save(OrderDetails orderDetails) {
        return repo.save(orderDetails);
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }

    @Override
    public OrderDetails findById(int id) {
        return null;
    }
}
