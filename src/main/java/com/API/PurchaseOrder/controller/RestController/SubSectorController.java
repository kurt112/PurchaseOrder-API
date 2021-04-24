package com.API.PurchaseOrder.controller.RestController;

import com.API.PurchaseOrder.entity.SubSector;
import com.API.PurchaseOrder.service.serviceImplementation.SubSectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/subSector")
public class SubSectorController {

    private final SubSectorService subSectorService;

    @Autowired
    public SubSectorController(SubSectorService subSectorService) {
        this.subSectorService = subSectorService;
    }

    @PostMapping("/addUpdate")
    private ResponseEntity<List<HashMap>> addSector(@RequestBody SubSector sector){
        SubSector find = subSectorService.findById(sector.getId());
        HashMap<String, Object> hashMap = new HashMap<>();
        List<HashMap> response = new ArrayList<>();

        if(find == null){
            sector.setCreatedAt(new Date());
            hashMap.put("message","Sector Register Success");
        }else{
            sector.setUpdatedAt(new Date());
            hashMap.put("message","Sector Update Success");
        }

        hashMap.put("data",sector);

        subSectorService.save(sector);

        response.add(hashMap);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/list")
    private ResponseEntity<?> sectorList(@RequestParam("search") String search, @RequestParam("page") int page,
                                         @RequestParam("size") int size){
        HashMap<String, Object> response = new HashMap<>();
        Page<SubSector> sectors = subSectorService.data(search,page-1,size,"",false);

        response.put("data", sectors.getContent());
        response.put("totalElements", sectors.getTotalElements());
        response.put("totalPages", sectors.getTotalPages());
        response.put("page", sectors.getNumber()+1);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getSector")
    private ResponseEntity<?> sector(@RequestParam("id") int id){
        SubSector sector = subSectorService.findById(id);

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

        SubSector sector = subSectorService.findById(id);

        HashMap<String, Object> hashMap = new HashMap<>();
        List<SubSector> sectors = new ArrayList<>();
        List<HashMap> response = new ArrayList<>();
        sector.setDeletedAt(new Date());
        sector.setStatus(status);
        sectors.add(sector);
        response.add(hashMap);
        hashMap.put("message","Sector Delete Success");
        hashMap.put("data",sectors);
        return ResponseEntity.ok(response);
    }

}
