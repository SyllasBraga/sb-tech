package com.sb.tech.controllers;

import com.sb.tech.api.TechnicianApi;
import com.sb.tech.dtos.TechnicianDto;
import com.sb.tech.models.TechnicianModel;
import com.sb.tech.services.impl.TechnicianServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TechnicianController implements TechnicianApi {

    public final TechnicianServiceImpl technicianServiceImpl;

    public TechnicianController(TechnicianServiceImpl technicianServiceImpl) {
        this.technicianServiceImpl = technicianServiceImpl;
    }

    @Override
    public ResponseEntity<TechnicianDto> getById(@PathVariable String id){
        return ResponseEntity.ok(TechnicianDto.toTechnicianDto(technicianServiceImpl.getByUuid(id)));
    }

    @Override
    public ResponseEntity<TechnicianDto> getByDocument(@RequestParam String document){
        return ResponseEntity.ok(TechnicianDto.toTechnicianDto(technicianServiceImpl.getTechnicianByDocument(document)));
    }

    @Override
    public ResponseEntity<TechnicianDto> insert(@RequestBody @Valid  TechnicianDto technicianDto){
        TechnicianModel technicianSaved = TechnicianModel.toTechnicianModel(technicianDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(TechnicianDto.toTechnicianDto(technicianServiceImpl.insertTechnician(technicianSaved)));
    }

    @Override
    public ResponseEntity<TechnicianDto> update(@PathVariable String id, @Valid @RequestBody TechnicianDto technicianDto){
        TechnicianModel technicianSaved = TechnicianModel.toTechnicianModel(technicianDto);
        return ResponseEntity.ok(TechnicianDto.toTechnicianDto(technicianServiceImpl.updateTechnician(id, technicianSaved)));
    }

    @Override
    public ResponseEntity<TechnicianDto> delete(@PathVariable String id){
        technicianServiceImpl.deleteTechnician(id);
        return ResponseEntity.noContent().build();
    }
}
