package com.API.PurchaseOrder.controller.RestController;

import com.API.PurchaseOrder.entity.API.SettingByStatus;
import com.API.PurchaseOrder.entity.Approval;
import com.API.PurchaseOrder.entity.Order;
import com.API.PurchaseOrder.service.serviceImplementation.ApprovalService;
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
@RequestMapping("/approval")
public class ApprovalController {
    private final ApprovalService service;
    private final OrderDetailsService orderDetailsService;

    @Autowired
    public ApprovalController(ApprovalService service, OrderDetailsService orderDetailsService) {
        this.service = service;
        this.orderDetailsService = orderDetailsService;
    }

    @PostMapping("/list")
    private ResponseEntity<?> approvalList(@RequestBody SettingByStatus settings){
        HashMap<String, Object> response = new HashMap<>();
        Page<Approval> products = service.getApproval(settings.getSearch(),
                settings.getCurrentPage()-1,settings.getPageSize(),settings.getStatus());
        response.put("data", products.getContent());
        response.put("totalElements", products.getTotalElements());
        response.put("totalPages", products.getTotalPages());
        response.put("page", products.getNumber()+1);
        return ResponseEntity.ok(response);
    }
}
