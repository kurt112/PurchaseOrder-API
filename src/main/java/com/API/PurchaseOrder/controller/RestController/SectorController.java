package com.API.PurchaseOrder.controller.RestController;

import com.API.PurchaseOrder.entity.Sector;
import com.API.PurchaseOrder.service.serviceImplementation.SectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
        Sector find = sectorService.findById(sector.getId());
        HashMap<String, Object> hashMap = new HashMap<>();
        List<HashMap> response = new ArrayList<>();

        if(find == null){
            sector.setCreateAt(new Date());
            hashMap.put("message","Sector Register Success");
        }else{
            sector.setUpdateAt(new Date());
            hashMap.put("message","Sector Update Success");
        }

        hashMap.put("data",sector);

        sectorService.save(sector);

        response.add(hashMap);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/list")
    private ResponseEntity<?> sectorList(@RequestParam("search") String search, @RequestParam("page") int page,
                                         @RequestParam("size") int size){
        HashMap<String, Object> response = new HashMap<>();
        Page<Sector> sectors = sectorService.data(search,page-1,size,"", true);

        response.put("data", sectors.getContent());
        response.put("totalElements", sectors.getTotalElements());
        response.put("totalPages", sectors.getTotalPages());
        response.put("page", sectors.getNumber()+1);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getSector")
    private ResponseEntity<?> sector(@RequestParam("id") int id){
        Sector sector = sectorService.findById(id);

        HashMap<String, Object> response = new HashMap<>();

        if(sector == null){
            response.put("message", "Can't find user with the id of " + id);
            return ResponseEntity.badRequest().body(response);
        }

        response.put("data", sector);
        response.put("message", "Sector Find Success");

        return ResponseEntity.ok(response);
    }

    // 0 or 1
    @PostMapping("/updateDeleteStatus")
    private ResponseEntity<List<HashMap>> updateDeleteStatus(@RequestParam("sector-id") int id, @RequestParam("status") int status){

        Sector sector = sectorService.findById(id);

        HashMap<String, Object> hashMap = new HashMap<>();
        List<Sector> sectors = new ArrayList<>();
        List<HashMap> response = new ArrayList<>();
        sector.setDeleteAt(new Date());
        sector.setStatus(status);
        sectors.add(sector);
        response.add(hashMap);
        hashMap.put("message","Sector Delete Success");
        hashMap.put("data",sectors);
        return ResponseEntity.ok(response);
    }

}
