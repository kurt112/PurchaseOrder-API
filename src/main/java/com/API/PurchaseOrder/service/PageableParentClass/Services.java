package com.API.PurchaseOrder.service.PageableParentClass;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface Services<T> {
    Page<T> data(String search, int page, int size);

    T save(T t);

    boolean deleteById(int id);

    T findById(int id);
}
