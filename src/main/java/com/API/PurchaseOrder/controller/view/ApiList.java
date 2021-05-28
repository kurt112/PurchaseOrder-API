package com.API.PurchaseOrder.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ApiList {

    @GetMapping("/")
    public String Weclome(){
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
