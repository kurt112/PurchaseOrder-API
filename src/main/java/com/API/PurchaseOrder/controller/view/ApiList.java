package com.API.PurchaseOrder.controller.view;

import com.API.PurchaseOrder.entity.*;
import com.API.PurchaseOrder.service.serviceImplementation.*;
import com.fasterxml.uuid.Generators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;

import static com.fasterxml.jackson.databind.jsonFormatVisitors.JsonValueFormat.UUID;

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

        userService.save(new User(0,"kurtad@gmail.com","123","456", "Kurt", "Orioque",1,2,sectorService.findById(1),new Date(),new Date(),null));
        userService.save(new User(0,"JohnDoe@gmail.com","johndoe","1234567","John","Doe",1,1,sectorService.findById(2),new Date(),new Date(), new Date()));

        subSectorService.save(new SubSector(0, "Drugs", 1, new Date(),new Date(), null));
        subSectorService.save(new SubSector(0, "Foods", 1, new Date(),new Date(), null));
        subSectorService.save(new SubSector(0, "Chips", 1, new Date(),new Date(), null));
        subSectorService.save(new SubSector(0, "T Shirt", 1, new Date(),new Date(), null));

        supplierService.save(new Supplier(0,"John", "JownDeo","444-233","+639 322 133",1, new Date(), new Date(),null));
        supplierService.save(new Supplier(0,"Armin", "John","444-233","+639 322 133",1, new Date(), new Date(),null));
        supplierService.save(new Supplier(0,"Mark", "Doe","444-233","+639 322 133",1, new Date(), new Date(),null));

        productService.save(new Product(0, Generators.randomBasedGenerator().generate().toString().substring(0,7), supplierService.findById(1),subSectorService.findById(1),"Biogesic", "For flu and body pain", Generators.randomBasedGenerator().generate().toString().substring(0,7), "33.2 MG", 21,1,new Date(),new Date(),null));
        productService.save(new Product(0, Generators.randomBasedGenerator().generate().toString().substring(0,7), supplierService.findById(1),subSectorService.findById(1),"Bioflu", "For flu and body pain", Generators.randomBasedGenerator().generate().toString().substring(0,7), "33.2 MG", 21,1,new Date(),new Date(),null));
        productService.save(new Product(0, Generators.randomBasedGenerator().generate().toString().substring(0,7), supplierService.findById(1),subSectorService.findById(1),"Neozep", "FOr Cold ", Generators.randomBasedGenerator().generate().toString().substring(0,7), "33.2 MG", 21,1,new Date(),new Date(),null));
        productService.save(new Product(0, Generators.randomBasedGenerator().generate().toString().substring(0,7), supplierService.findById(1),subSectorService.findById(1),"Alaxan", "For Body Pain", Generators.randomBasedGenerator().generate().toString().substring(0,7), "33.2 MG", 21,1,new Date(),new Date(),null));
        productService.save(new Product(0, Generators.randomBasedGenerator().generate().toString().substring(0,7), supplierService.findById(1),subSectorService.findById(1),"Glowee", "Vitamis For Food", Generators.randomBasedGenerator().generate().toString().substring(0,7), "33.2 MG", 21,1,new Date(),new Date(),null));


        productService.save(new Product(0, Generators.randomBasedGenerator().generate().toString().substring(0,7), supplierService.findById(2),subSectorService.findById(2),"Oishi Prawn Cracker", "With a similar texture of American hot fries, this foreign snack is a crunchy treat infused with a real shrimp flavor.", Generators.randomBasedGenerator().generate().toString().substring(0,7), "60g", 20,1,new Date(),new Date(),null));
        productService.save(new Product(0, Generators.randomBasedGenerator().generate().toString().substring(0,7), supplierService.findById(2),subSectorService.findById(2),"Pillows", "Crunchy crackers bursting with yummy chocolate filling. -Chocolate filled -Crunchy and fun to eat -Enriched with Vitamin A -For baon or as snacks.", Generators.randomBasedGenerator().generate().toString().substring(0,7), "60g", 20,1,new Date(),new Date(),null));
        productService.save(new Product(0, Generators.randomBasedGenerator().generate().toString().substring(0,7), supplierService.findById(2),subSectorService.findById(2),"kirei", "Enjoy these delicious Oishi Kirei Yummy Flakes. These chips feature a shrimp flavor that tantilizes your taste buds with a hint of saltiness.", Generators.randomBasedGenerator().generate().toString().substring(0,7), "60g", 20,1,new Date(),new Date(),null));
        productService.save(new Product(0, Generators.randomBasedGenerator().generate().toString().substring(0,7), supplierService.findById(2),subSectorService.findById(2),"cracklings oishi", "Shop Oishi Classic Ribbed Cracklings In Old Fashioned Salt and Vinegar - compare prices,.", Generators.randomBasedGenerator().generate().toString().substring(0,7), "60g", 20,1,new Date(),new Date(),null));
        productService.save(new Product(0, Generators.randomBasedGenerator().generate().toString().substring(0,7), supplierService.findById(2),subSectorService.findById(2),"Crispy Patata", "Pillows - Oishi. Crunchy crackers bursting with yummy chocolate filling - Chocolate filled -Crunchy and fun to eat -Enriched with Vitamin A -For baon or as snacks.", Generators.randomBasedGenerator().generate().toString().substring(0,7), "60g", 20,1,new Date(),new Date(),null));



        productService.save(new Product(0, Generators.randomBasedGenerator().generate().toString().substring(0,7), supplierService.findById(3),subSectorService.findById(4),"The Black Google Tee", " 100% cotton Google t-shirt", Generators.randomBasedGenerator().generate().toString().substring(0,7), "Cm", 100,1,new Date(),new Date(),null));
        productService.save(new Product(0, Generators.randomBasedGenerator().generate().toString().substring(0,7), supplierService.findById(3),subSectorService.findById(4),"The Black Google Tee", " 100% cotton Google t-shirt", Generators.randomBasedGenerator().generate().toString().substring(0,7), "Cm", 150,1,new Date(),new Date(),null));
        productService.save(new Product(0, Generators.randomBasedGenerator().generate().toString().substring(0,7), supplierService.findById(3),subSectorService.findById(4),"The Black Google Tee", "100% cotton Google t-shirt", Generators.randomBasedGenerator().generate().toString().substring(0,7), "Cm", 150,1,new Date(),new Date(),null));
        productService.save(new Product(0, Generators.randomBasedGenerator().generate().toString().substring(0,7), supplierService.findById(3),subSectorService.findById(4),"The Black Google Tee", "100% cotton Google t-shirt", Generators.randomBasedGenerator().generate().toString().substring(0,7), "Cm", 200,1,new Date(),new Date(),null));
        productService.save(new Product(0, Generators.randomBasedGenerator().generate().toString().substring(0,7), supplierService.findById(3),subSectorService.findById(4),"The Black Google Tee", "100% cotton Google t-shirt", Generators.randomBasedGenerator().generate().toString().substring(0,7), "Cm", 300,1,new Date(),new Date(),null));



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
