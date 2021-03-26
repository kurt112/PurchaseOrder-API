package com.API.PurchaseOrder.service.PageableParentClass;



import com.API.PurchaseOrder.entity.API.ApiSettings;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ServicesGraphQl<T> {
    List<T> data(String search, int page, int size);

    T save(T t);

    boolean deleteById(int id);

    T findById(int id);

    ApiSettings apiSettings();
}
