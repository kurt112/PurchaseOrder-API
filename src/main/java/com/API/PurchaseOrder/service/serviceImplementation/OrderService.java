package com.API.PurchaseOrder.service.serviceImplementation;

import com.API.PurchaseOrder.entity.Order;
import com.API.PurchaseOrder.repository.OrderRepository;
import com.API.PurchaseOrder.service.PageableParentClass.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class OrderService implements Services<Order> {

    private final OrderRepository repo;

    @Autowired
    public OrderService(OrderRepository repo) {
        this.repo = repo;
    }

    public Page<Order> getOrder(String search, int page,int size,int status, int requestorId, int approvalId){

        Pageable pageable = PageRequest.of(page,size);
        if(requestorId == -1 && approvalId == -1){
            return repo.getOrder(status,pageable);
        }else if(approvalId == -1){
            return repo.getOrderWithRequestor(status,requestorId,pageable);
        }else if(requestorId == -1){
            return repo.getOrderWithApproval(status,approvalId,pageable);
        }

        return repo.getOrderWithRequestorAndApproval(status,requestorId,approvalId,pageable);

    }

    @Override
    public Page<Order> data(String search, int page, int size, String orderBy, boolean order) {
        return null;
    }

    @Override
    public Order save(Order order) {

        return repo.save(order);
    }

    @Override
    public boolean deleteById(int id) {
        repo.deleteById(id);
        return true;
    }

    @Override
    public Order findById(int id) {
        Optional<Order> order = repo.findById(id);
        return order.orElse(null);
    }
}
