package com.API.PurchaseOrder.controller.RestController;

import com.API.PurchaseOrder.service.serviceImplementation.ApprovalService;
import com.API.PurchaseOrder.service.serviceImplementation.OrderDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
