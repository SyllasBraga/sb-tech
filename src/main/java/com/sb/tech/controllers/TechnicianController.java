package com.sb.tech.controllers;

import com.sb.tech.dtos.TechnicianDto;
import com.sb.tech.models.TechnicianModel;
import com.sb.tech.services.TechnicianService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/technician")
public class TechnicianController {

    public final TechnicianService technicianService;

    public TechnicianController(TechnicianService technicianService) {
        this.technicianService = technicianService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TechnicianDto> getById(@PathVariable String id){
        return ResponseEntity.ok(TechnicianDto.toTechnicianDto(technicianService.getByUuid(id)));
    }

    @GetMapping("")
    public ResponseEntity<TechnicianDto> getByDocument(@RequestParam String document){
        return ResponseEntity.ok(TechnicianDto.toTechnicianDto(technicianService.getTechnicianByDocument(document)));
    }

    @PostMapping
    public ResponseEntity<TechnicianDto> insert(@RequestBody TechnicianDto technicianDto){
        TechnicianModel technicianSaved = TechnicianModel.toTechnicianModel(technicianDto);
        return ResponseEntity.ok(TechnicianDto.toTechnicianDto(technicianService.insertTechnician(technicianSaved)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TechnicianDto> update(@PathVariable String id,  @RequestBody TechnicianDto technicianDto){
        TechnicianModel technicianSaved = TechnicianModel.toTechnicianModel(technicianDto);
        return ResponseEntity.ok(TechnicianDto.toTechnicianDto(technicianService.updateTechnician(id, technicianSaved)));
    }

}
