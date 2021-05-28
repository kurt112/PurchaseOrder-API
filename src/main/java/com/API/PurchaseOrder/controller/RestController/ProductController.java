package com.API.PurchaseOrder.controller.RestController;


import com.API.PurchaseOrder.entity.API.ProductSettings;
import com.API.PurchaseOrder.entity.Product;
import com.API.PurchaseOrder.entity.SubSector;
import com.API.PurchaseOrder.entity.Supplier;
import com.API.PurchaseOrder.service.serviceImplementation.ProductService;
import com.API.PurchaseOrder.service.serviceImplementation.SubSectorService;
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
@RequestMapping("/product")
public class ProductController {

    private final ProductService service;
    private final SubSectorService subSectorService;
    private final SupplierService supplierService;

    @Autowired
    public ProductController(ProductService service, SubSectorService subSectorService, SupplierService supplierService) {
        this.service = service;
        this.subSectorService = subSectorService;
        this.supplierService = supplierService;
    }

    @PostMapping("/list")
    private ResponseEntity<?> ProductList(@RequestBody ProductSettings settings){
        HashMap<String, Object> response = new HashMap<>();
        Page<Product> products = service.getProduct(settings.getSearch(),
                settings.getCurrentPage()-1,settings.getPageSize(),settings.getSupplierId(),
                settings.getSubSectorId(),settings.getStatus());
        response.put("data", products.getContent());
        response.put("totalElements", products.getTotalElements());
        response.put("totalPages", products.getTotalPages());
        response.put("page", products.getNumber()+1);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/addUpdate")
    public ResponseEntity<?> CreateProduct(@RequestBody HashMap<?,?> hashMap){

        Object id = hashMap.get("id");
        Object code = hashMap.get("code");
        Object supplierId = hashMap.get("supplierId");
        Object subSectorId = hashMap.get("subSectorId");
        Object name = hashMap.get("name");
        Object description = hashMap.get("description");
        Object sku = hashMap.get("sku");
        Object unitMeasure  = hashMap.get("unitMeasure");
        Object price = hashMap.get("price");
        Object status = hashMap.get("status");

        Supplier supplier = supplierService.findById(Integer.parseInt(supplierId.toString()));
        SubSector subSector = subSectorService.findById(Integer.parseInt(subSectorId.toString()));

        Product product = null;

        if(id != null){
            product = service.findById(Integer.parseInt(id.toString()));
        }

        List<Product> list = new ArrayList<>();
        HashMap<String, Object> result = new HashMap<>();

        if(product == null){
            product = new Product(0,code.toString(), supplier,subSector,name.toString(),description.toString(),sku.toString(),unitMeasure.toString()
                    , Integer.parseInt(price.toString()), Integer.parseInt(status.toString()), new Date(),null,null);
            result.put("message", "User Register Successful");
        }else{
            product.setUpdateAt(new Date());
            result.put("message", "User Update Successful");
        }



        if(service.save(product) == null){
            result.put("message", "Can't Add User");
            return ResponseEntity.badRequest().body(hashMap);
        }
        list.add(product);
        result.put("data",list);
        result.put("success",true);

        return ResponseEntity.ok(result);
    }


    @GetMapping("/delete")
    public ResponseEntity<?> DeleteProduct  (@RequestParam("id") int id){
        Product product = service.findById(id);
        HashMap<String, Object> hashMap = new HashMap<>();
        if(product == null){
            hashMap.put("message", "Can't Delete Product With the id of " + id);

            return ResponseEntity.badRequest().body(hashMap);
        }


        product.setDeleteAt(new Date());
        hashMap.put("message", "Delete Delete Successful");
        hashMap.put("data", product);
        return ResponseEntity.ok(hashMap);
    }

    @GetMapping("/getProduct")
    public ResponseEntity<?> getSupplier(@RequestParam("id") int id){

        HashMap<String, Object> response = new HashMap<>();
        Product product = service.findById(id);
        List<Product> productList = new ArrayList<>();

        if(product == null){
            response.put("message", "Can't find Supplier with the id of " + id);
            return ResponseEntity.badRequest().body(response);
        }
        productList.add(product);
        response.put("data", productList);
        response.put("message", "Product Find Success");
        response.put("success",true);
        return ResponseEntity.ok(response);
    }
}
