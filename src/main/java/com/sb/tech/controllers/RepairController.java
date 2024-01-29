package com.sb.tech.controllers;

import com.sb.tech.dtos.RepairDto;
import com.sb.tech.models.RepairModel;
import com.sb.tech.services.RepairService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/repair")
public class RepairController {

    private final RepairService repairService;

    public RepairController(RepairService repairService) {
        this.repairService = repairService;
    }

    @GetMapping
    public ResponseEntity<List<RepairDto>> getAll(){
        List<RepairDto> listDto = new ArrayList<>();
        List<RepairModel> listModel = repairService.getAll();
        listModel.forEach(repairModel -> listDto.add(RepairDto.toRepairDto(repairModel)));
        return ResponseEntity.ok(listDto);
    }
}
