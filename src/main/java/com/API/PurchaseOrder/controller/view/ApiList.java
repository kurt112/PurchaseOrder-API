package com.API.PurchaseOrder.controller.view;

import com.API.PurchaseOrder.entity.*;
import com.API.PurchaseOrder.service.serviceImplementation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;

@Controller
public class ApiList {

    private final SubSectorService subSectorService;
    private final ProductService productService;
    private final SectorService sectorService;
    private final UserService userService;
    private final SupplierService supplierService;

    @Autowired
    public ApiList(SubSectorService subSectorService, ProductService productService, SectorService sectorService, UserService userService, SupplierService supplierService) {
        this.subSectorService = subSectorService;
        this.productService = productService;
        this.sectorService = sectorService;
        this.userService = userService;
        this.supplierService = supplierService;
    }

    @GetMapping("/")
    public String Weclome(){

        sectorService.save(new Sector(1,"Sector 1",1,2,new Date(), new Date(), null));
        sectorService.save(new Sector(2,"Sector 2",1,2,new Date(), new Date(), null));
        sectorService.save(new Sector(3,"Sector 3",1,2,new Date(), new Date(), null));

        userService.save(new User(1,"kurtad@gmail.com","123","456", "Kurt", "lastName",1,2,sectorService.findById(1),new Date(),new Date(),null));

        subSectorService.save(new SubSector(1, "SubSect 2", 1, new Date(),new Date(), null));
        subSectorService.save(new SubSector(2, "SubSect 2", 1, new Date(),new Date(), null));
        subSectorService.save(new SubSector(3, "SubSect 3", 1, new Date(),new Date(), null));

        supplierService.save(new Supplier(1,"John", "JownDeo","444-233","+639 322 133",1, new Date(), new Date(),null));
        supplierService.save(new Supplier(2,"Armin", "John","444-233","+639 322 133",1, new Date(), new Date(),null));
        supplierService.save(new Supplier(3,"Mark", "Doe","444-233","+639 322 133",1, new Date(), new Date(),null));


        productService.save(new Product(1, "code123", supplierService.findById(1),subSectorService.findById(1),"product 1", "saple Product", "SKU", "33.2 MG", 30,1,new Date(),new Date(),null));
        productService.save(new Product(2, "ccde122", supplierService.findById(2),subSectorService.findById(1),"product 1", "saple Product", "SKU", "33.2 MG", 30,1,new Date(),new Date(),null));
        productService.save(new Product(3, "code122", supplierService.findById(3),subSectorService.findById(1),"product 1", "saple Product", "SKU", "33.2 MG", 30,1,new Date(),new Date(),null));

        return "user";
    }

    @GetMapping("/sector")
    public String Sector(){
        return "sector";
    }

    @GetMapping("/subsector")
    public String SubSector(){
        return "subsector";
    }

    @GetMapping("/product")
    public String Product() {return "product";}

    @GetMapping("/supplier")
    public String Supplier() {return "supplier";}
}
