package com.API.PurchaseOrder.controller.RestController;


import com.API.PurchaseOrder.entity.API.Settings;
import com.API.PurchaseOrder.entity.Supplier;
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

    private final SupplierService service;

    @Autowired
    public SupplierController(SupplierService service) {
        this.service = service;
    }

    @PostMapping("/addUpdate")
    public ResponseEntity<?> createSupplier(@RequestBody Supplier supplier){

        Supplier find = service.findById(supplier.getId());
        List<Supplier> list = new ArrayList<>();
        HashMap<String, Object> hashMap = new HashMap<>();
        if(find == null){
            supplier.setCreateAt(new Date());
            hashMap.put("message", "Supplier Register Successful");
        }else{
            supplier.setUpdateAt(new Date());
            hashMap.put("message", "Supplier Update Successful");
        }
        if(service.save(supplier) == null){
            hashMap.put("message", "Can't Add Supplier");
            return ResponseEntity.badRequest().body(hashMap);
        }
        list.add(supplier);
        hashMap.put("data",list);
        hashMap.put("success",true);

        return ResponseEntity.ok(hashMap);
    }


    @PostMapping("/list")
    public ResponseEntity<?> getSupplier(@RequestBody Settings settings){
        HashMap<String, Object> response = new HashMap<>();
        Page<Supplier> suppliers = service.data(settings.getSearch(), settings.getCurrentPage()-1,settings.getPageSize(),settings.getOrderBy(),false);

        response.put("totalItem", suppliers.getTotalElements());
        response.put("totalPage", suppliers.getTotalPages());
        response.put("pageSize", settings.getPageSize());
        response.put("currentPage", suppliers.getNumber()+1);
        response.put("status", true);
        response.put("data", suppliers.getContent());


        return ResponseEntity.ok(response);
    }

    @GetMapping("/delete")
    public ResponseEntity<?> deleteSupplier(@RequestParam("id") int id){
        Supplier supplier = service.findById(id);
        HashMap<String, Object> hashMap = new HashMap<>();
        if(supplier == null){
            hashMap.put("message", "Can't Delete Supplier With the id of " + id);

            return ResponseEntity.badRequest().body(hashMap);
        }


        supplier.setDeleteAt(new Date());
        hashMap.put("message", "Delete Supplier Successful");
        hashMap.put("data", supplier);
        return ResponseEntity.ok(hashMap);
    }

    @GetMapping("/getSupplier")
    public ResponseEntity<?> getSupplier(@RequestParam("id") int id){

        HashMap<String, Object> response = new HashMap<>();
        Supplier supplier = service.findById(id);
        List<Supplier> supplierList = new ArrayList<>();

        if(supplier == null){
            response.put("message", "Can't find Supplier with the id of " + id);
            return ResponseEntity.badRequest().body(response);
        }
        supplierList.add(supplier);
        response.put("data", supplierList);
        response.put("message", "Supplier Find Success");
        response.put("success",true);
        return ResponseEntity.ok(response);
    }
}
