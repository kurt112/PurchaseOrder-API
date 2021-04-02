package com.API.PurchaseOrder.service.PageableParentClass;



import com.API.PurchaseOrder.entity.API.ApiSettings;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ServicesGraphQl<T> {
    Page<T> data(String search, int page, int size);

    T save(T t);

    boolean deleteById(int id);

    T findById(int id);
}
