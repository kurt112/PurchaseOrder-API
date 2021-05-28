package com.API.PurchaseOrder.service.serviceImplementation;

import com.API.PurchaseOrder.entity.Approval;
import com.API.PurchaseOrder.repository.ApprovalRepository;
import com.API.PurchaseOrder.service.PageableParentClass.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ApprovalService implements Services<Approval> {

    private final ApprovalRepository repo;


    @Autowired
    public ApprovalService(ApprovalRepository repo) {
        this.repo = repo;
    }

    public Page<Approval> getApproval(String search, int page, int size, int status){
        Pageable pageable = PageRequest.of(page,size);
        return repo.getApproval(status,pageable);
    }

    @Override
    public Page<Approval> data(String search, int page, int size, String orderBy, boolean order) {
        return null;
    }

    @Override
    public Approval save(Approval approval) {
        return repo.save(approval);
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }

    @Override
    public Approval findById(int id) {
        return null;
    }
}
