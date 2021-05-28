package com.API.PurchaseOrder.controller.RestController;


import com.API.PurchaseOrder.entity.API.Settings;
import com.API.PurchaseOrder.entity.API.UserGet;
import com.API.PurchaseOrder.entity.Product;
import com.API.PurchaseOrder.entity.Sector;
import com.API.PurchaseOrder.entity.User;
import com.API.PurchaseOrder.service.serviceImplementation.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService service;

    @Autowired
    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping("/list")
    private ResponseEntity<?> sectorList(@RequestParam("search") String search, @RequestParam("page") int page,
                                         @RequestParam("size") int size){
        HashMap<String, Object> response = new HashMap<>();
        Page<Product> sectors = service.data(search,page-1,size,"", true);

        response.put("data", sectors.getContent());
        response.put("totalElements", sectors.getTotalElements());
        response.put("totalPages", sectors.getTotalPages());
        response.put("page", sectors.getNumber()+1);
        return ResponseEntity.ok(response);
    }

//    @PostMapping("/addUpdate")
//    public ResponseEntity<?> product(@RequestBody User user){
//
//        User find = userService.findById(user.getId());
//        List<User> list = new ArrayList<>();
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
