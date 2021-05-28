package com.API.PurchaseOrder.service.serviceImplementation;

import com.API.PurchaseOrder.entity.Product;
import com.API.PurchaseOrder.repository.ProductRepository;
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
public class ProductService implements Services<Product> {
    private final ProductRepository repo;

    @Autowired
    public ProductService(ProductRepository repo) {
        this.repo = repo;
    }

    @Override
    public Page<Product> data(String search, int page, int size, String orderBy, boolean order) {
        Pageable pageable = PageRequest.of(page,size);
        return repo.findAll(pageable);
    }

    @Override
    public Product save(Product product) {
        return repo.save(product);
    }

    @Override
    public boolean deleteById(int id) {
        repo.deleteById(id);
        return true;
    }

    @Override
    public Product findById(int id) {
        Optional<Product> product = repo.findById(id);
        return product.orElse(null);
    }
}
