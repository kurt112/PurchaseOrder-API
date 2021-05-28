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
        userService.save(new User(2,"JohnDoe@gmail.com","johndoe","1234567","John","Doe",1,1,sectorService.findById(2),new Date(),new Date(), new Date()));

        subSectorService.save(new SubSector(1, "Drugs", 1, new Date(),new Date(), null));
        subSectorService.save(new SubSector(2, "Foods", 1, new Date(),new Date(), null));
        subSectorService.save(new SubSector(3, "Chips", 1, new Date(),new Date(), null));

        supplierService.save(new Supplier(1,"John", "JownDeo","444-233","+639 322 133",1, new Date(), new Date(),null));
        supplierService.save(new Supplier(2,"Armin", "John","444-233","+639 322 133",1, new Date(), new Date(),null));
        supplierService.save(new Supplier(3,"Mark", "Doe","444-233","+639 322 133",1, new Date(), new Date(),null));


        productService.save(new Product(1, "ass-ffe", supplierService.findById(1),subSectorService.findById(1),"Biogesic", "For flu and body pain", "SKU", "33.2 MG", 21,1,new Date(),new Date(),null));



        productService.save(new Product(2, "ffss-ffa", supplierService.findById(2),subSectorService.findById(1),"Oishi Prawn Cracker", "With a similar texture of American hot fries, this foreign snack is a crunchy treat infused with a real shrimp flavor.", "SKU", "60g", 20,1,new Date(),new Date(),null));
        productService.save(new Product(3, "ggg-aaf", supplierService.findById(3),subSectorService.findById(1),"Oreo", " is an American sandwich cookie consisting of two (usually chocolate) wafers with a sweet crème filling. Introduced on March 6, 1912, Oreo is the best-selling cookie brand in the United States.", "SKU", "100g", 30,1,new Date(),new Date(),null));
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
