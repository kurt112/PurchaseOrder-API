package com.API.PurchaseOrder.service.serviceImplementation;

import com.API.PurchaseOrder.entity.Supplier;
import com.API.PurchaseOrder.repository.SupplierRepository;
import com.API.PurchaseOrder.service.PageableParentClass.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SupplierService implements Services<Supplier> {

    private final SupplierRepository repo;

    @Autowired
    public SupplierService(SupplierRepository repo) {
        this.repo = repo;
    }


    @Override
    public Page<Supplier> data(String search, int page, int size, String orderBy, boolean order) {
        Pageable pageable = PageRequest.of(page,size);
        return repo.findAll(pageable);
    }

    @Override
    public Supplier save(Supplier supplier) {
        return repo.save(supplier);
    }

    @Override
    public boolean deleteById(int id) {
        repo.deleteById(id);
        return true;
    }

    @Override
    public Supplier findById(int id) {
        Optional<Supplier> supplier  = repo.findById(id);
        return supplier.orElse(null);
    }

    public List<Supplier> getAllData(){
        return repo.findAll();
    }
}
