package com.sb.tech.controllers;

import com.sb.tech.api.TechnicianApi;
import com.sb.tech.dtos.TechnicianDto;
import com.sb.tech.models.TechnicianModel;
import com.sb.tech.services.TechnicianService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TechnicianController implements TechnicianApi {

    public final TechnicianService technicianService;

    public TechnicianController(TechnicianService technicianService) {
        this.technicianService = technicianService;
    }

    @Override
    public ResponseEntity<TechnicianDto> getById(@PathVariable String id){
        return ResponseEntity.ok(TechnicianDto.toTechnicianDto(technicianService.getByUuid(id)));
    }

    @Override
    public ResponseEntity<TechnicianDto> getByDocument(@RequestParam String document){
        return ResponseEntity.ok(TechnicianDto.toTechnicianDto(technicianService.getTechnicianByDocument(document)));
    }

    @Override
    public ResponseEntity<TechnicianDto> insert(@RequestBody @Valid  TechnicianDto technicianDto){
        TechnicianModel technicianSaved = TechnicianModel.toTechnicianModel(technicianDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(TechnicianDto.toTechnicianDto(technicianService.insertTechnician(technicianSaved)));
    }

    @Override
    public ResponseEntity<TechnicianDto> update(@PathVariable String id, @Valid @RequestBody TechnicianDto technicianDto){
        TechnicianModel technicianSaved = TechnicianModel.toTechnicianModel(technicianDto);
        return ResponseEntity.ok(TechnicianDto.toTechnicianDto(technicianService.updateTechnician(id, technicianSaved)));
    }

    @Override
    public ResponseEntity<TechnicianDto> delete(@PathVariable String id){
        technicianService.deleteTechnician(id);
        return ResponseEntity.noContent().build();
    }
}
