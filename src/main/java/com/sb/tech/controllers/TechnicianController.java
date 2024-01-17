package com.sb.tech.controllers;

import com.sb.tech.dtos.TechnicianDto;
import com.sb.tech.services.TechnicianService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/document")
    public ResponseEntity<TechnicianDto> getByDocument(@RequestParam String document){
        return ResponseEntity.ok(TechnicianDto.toTechnicianDto(technicianService.getTechnicianByDocument(document)));
    }
}
