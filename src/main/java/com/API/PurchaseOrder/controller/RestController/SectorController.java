package com.API.PurchaseOrder.controller.RestController;

import com.API.PurchaseOrder.entity.Sector;
import com.API.PurchaseOrder.service.serviceImplementation.SectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/sector")
public class SectorController {

    private final SectorService sectorService;

    @Autowired
    public SectorController(SectorService sectorService) {
        this.sectorService = sectorService;
    }

    @PostMapping("/addUpdate")
    private ResponseEntity<List<HashMap>> addSector(@RequestBody Sector sector){
        HashMap<String, Object> hashMap = new HashMap<>();

        List<HashMap> response = new ArrayList<>();
//        Sector sector1 = new Sector(1,"kurt",1,2,3,new Date(),new Date(),new Date());

        sectorService.save(sector);
        hashMap.put("message","Register Success");
        hashMap.put("data",sector);
        response.add(hashMap);
        return ResponseEntity.ok(response);
    }

    // 0 or 1
    @PostMapping("/updateDeleteStatus")
    private ResponseEntity<List<HashMap>> updateDeleteStatus(@RequestParam("sector-id") int id, @RequestParam("status") int status){

        Sector sector = sectorService.findById(id);

        HashMap<String, Object> hashMap = new HashMap<>();
        List<Sector> sectors = new ArrayList<>();
        List<HashMap> response = new ArrayList<>();
        sector.setStatus(status);
        sectors.add(sector);
        response.add(hashMap);
        hashMap.put("message","Register Success");
        hashMap.put("data",sectors);
        response.add(hashMap);
        return ResponseEntity.ok(response);
    }

}
