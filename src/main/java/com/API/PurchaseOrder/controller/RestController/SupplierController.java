package com.API.PurchaseOrder.controller.RestController;


import com.API.PurchaseOrder.entity.API.Settings;
import com.API.PurchaseOrder.entity.API.UserGet;
import com.API.PurchaseOrder.entity.Supplier;
import com.API.PurchaseOrder.entity.User;
import com.API.PurchaseOrder.service.serviceImplementation.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/supplier")
public class SupplierController {

    private SupplierService service;

    @Autowired
    public SupplierController(SupplierService service) {
        this.service = service;
    }

//    @PostMapping("/addUpdate")
//    public ResponseEntity<?> createSupplier(@RequestBody Supplier supplier){
//
//        Supplier find = service.findById(supplier.getId());
//        List<Supplier> list = new ArrayList<>();
//        HashMap<String, Object> hashMap = new HashMap<>();
//        if(find == null){
//            user.setCreateAt(new Date());
//            hashMap.put("message", "User Register Successful");
//        }else{
//            user.setUpdateAt(new Date());
//            hashMap.put("message", "User Update Successful");
//        }
//        if(userService.save(user) == null){
//            hashMap.put("message", "Can't Add User");
//            return ResponseEntity.badRequest().body(hashMap);
//        }
//        list.add(user);
//        hashMap.put("data",list);
//        hashMap.put("success",true);
//
//        return ResponseEntity.ok(hashMap);
//    }
//
//
//    @PostMapping("/list")
//    public ResponseEntity<?> getUsers(@RequestBody Settings settings){
//        HashMap<String, Object> response = new HashMap<>();
//        Page<User> users = userService.data(settings.getSearch(), settings.getCurrentPage()-1,settings.getPageSize(),settings.getOrderBy(),false);
//        List<UserGet> userGets = new ArrayList<>();
//        users.getContent().forEach(e->{
//            userGets.add(new UserGet(e));
//        });
//        response.put("status", true);
//        response.put("totalItem", users.getTotalElements());
//        response.put("totalPage", users.getTotalPages());
//        response.put("pageSize", settings.getPageSize());
//        response.put("currentPage", users.getNumber()+1);
//        response.put("data", userGets);
//
//
//        return ResponseEntity.ok(response);
//    }
//
//    @PostMapping("/delete")
//    public ResponseEntity<?> deleteUser(@RequestParam("id") int id){
//        User user = userService.findById(id);
//        HashMap<String, Object> hashMap = new HashMap<>();
//        if(user == null){
//            hashMap.put("message", "Can't Delete User With the id of " + id);
//
//            return ResponseEntity.badRequest().body(hashMap);
//        }
//
//
//        user.setDeleteAt(new Date());
//        hashMap.put("message", "Delete User Successful");
//        hashMap.put("data", user);
//        return ResponseEntity.ok(hashMap);
//    }
}
