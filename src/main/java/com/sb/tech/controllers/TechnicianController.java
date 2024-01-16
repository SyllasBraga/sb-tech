package com.sb.tech.controllers;

import com.sb.tech.dtos.TechnicianDto;
import com.sb.tech.services.TechnicianService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/technician")
public class TechnicianController {

    public final TechnicianService technicianService;

    public TechnicianController(TechnicianService technicianService) {
        this.technicianService = technicianService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TechnicianDto> getById(@PathVariable String id){
        return ResponseEntity.ok(TechnicianDto.toTechnicianDto(technicianService.getByUuid(id)));
    }
}
