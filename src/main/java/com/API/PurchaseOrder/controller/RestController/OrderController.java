package com.API.PurchaseOrder.controller.RestController;

import com.API.PurchaseOrder.entity.API.SettingByStatus;
import com.API.PurchaseOrder.entity.Order;
import com.API.PurchaseOrder.entity.OrderDetails;
import com.API.PurchaseOrder.entity.Product;
import com.API.PurchaseOrder.service.serviceImplementation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService service;
    private final OrderDetailsService orderDetailsService;
    private final UserService userService;
    private final ProductService productService;

    @Autowired
    public OrderController(OrderService service, OrderDetailsService orderDetailsService, UserService userService, ProductService productService) {
        this.service = service;
        this.orderDetailsService = orderDetailsService;
        this.userService = userService;
        this.productService = productService;
    }

    @PostMapping("/list")
    private ResponseEntity<?>orderList(@RequestBody SettingByStatus settings){
        HashMap<String, Object> response = new HashMap<>();
        Page<Order> products = service.getOrder(settings.getSearch(),
                settings.getCurrentPage()-1,settings.getPageSize(),settings.getStatus(),settings.getRequestorId(),settings.getApprovalId());
        response.put("data", products.getContent());
        response.put("totalElements", products.getTotalElements());
        response.put("totalPages", products.getTotalPages());
        response.put("page", products.getNumber()+1);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/addUpdate")
    private ResponseEntity<?> addOrder(@RequestBody HashMap<?,?> hashMap){

        Object id = hashMap.get("id");
        Object products = hashMap.get("products");
        Object qty = hashMap.get("qty");

        Order order = null;

        List<Integer> productId = (List<Integer>) products;


        if(id != null){
            order = service.findById(Integer.parseInt(id.toString()));
        }

        List<Order> list = new ArrayList<>();
        HashMap<String, Object> result = new HashMap<>();
        List<Product> productList = new ArrayList<>();
        if(order == null){
            order = new Order(0,userService.findById(1),null,null,2,new Date(),new Date(),null);
            order.setQuantity(Integer.parseInt(qty.toString()));
            for(int i: productId){
                productList.add(productService.findById(i));
            }
        }else{
            order.setUpdatedAt(new Date());
            result.put("message", "User Update Successful");
        }

        if(service.save(order) == null){
              result.put("message", "Can't Add User");
            return ResponseEntity.badRequest().body(hashMap);
        }
        orderDetailsService.save(new OrderDetails(0,order,productList,productList.size(),3,new Date(), new Date(), null));

        list.add(order);
        result.put("data",list);
        result.put("success",true);
        return ResponseEntity.ok(result);
    }


    @GetMapping("/delete")
    public ResponseEntity<?> DeleteProduct  (@RequestParam("id") int id){
        Order order = service.findById(id);
        HashMap<String, Object> hashMap = new HashMap<>();
        if(order == null){
            hashMap.put("message", "Can't Delete Order With the id of " + id);

            return ResponseEntity.badRequest().body(hashMap);
        }


        order.setDeletedAt(new Date());
        hashMap.put("message", "Delete Delete Successful");
        hashMap.put("data", order);
        return ResponseEntity.ok(hashMap);
    }
}
