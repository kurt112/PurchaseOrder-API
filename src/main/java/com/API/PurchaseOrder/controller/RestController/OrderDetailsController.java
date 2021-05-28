package com.API.PurchaseOrder.controller.RestController;

import com.API.PurchaseOrder.entity.API.SettingByStatus;
import com.API.PurchaseOrder.entity.OrderDetails;
import com.API.PurchaseOrder.service.serviceImplementation.OrderDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/order-details")
public class OrderDetailsController {
    private final OrderDetailsService service;

    @Autowired
    public OrderDetailsController(OrderDetailsService service) {
        this.service = service;
    }

    @PostMapping("/list")
    private ResponseEntity<?> orderDetailsList(@RequestBody SettingByStatus settings){
        HashMap<String, Object> response = new HashMap<>();
        Page<OrderDetails> products = service.getOrderDetails(settings.getSearch(),
                settings.getCurrentPage()-1,settings.getPageSize(),settings.getStatus());
        response.put("data", products.getContent());
        response.put("totalElements", products.getTotalElements());
        response.put("totalPages", products.getTotalPages());
        response.put("page", products.getNumber()+1);
        return ResponseEntity.ok(response);
    }
}
