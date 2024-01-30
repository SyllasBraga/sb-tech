package com.sb.tech.controllers;

import com.sb.tech.dtos.BudgetDto;
import com.sb.tech.dtos.RepairDto;
import com.sb.tech.models.BudgetModel;
import com.sb.tech.models.RepairModel;
import com.sb.tech.services.RepairService;
import jakarta.validation.Valid;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/client")
    public ResponseEntity<RepairDto> getByClientDocument(@Valid @RequestParam(name = "document") @CPF String document){
        RepairDto repairDto = RepairDto.toRepairDto(repairService.getByClientDocument(document));
        return ResponseEntity.ok(repairDto);
    }

    @GetMapping("/technician")
    public ResponseEntity<RepairDto> getByTechnicianUuid(@RequestParam(name = "id") String id){
        RepairDto repairDto = RepairDto.toRepairDto(repairService.getByTechnicianId(id));
        return ResponseEntity.ok(repairDto);
    }

    @PostMapping
    public ResponseEntity<RepairDto> insert(@RequestBody RepairDto repairDto){
        RepairModel repairModel = repairService.insert(RepairModel.toRepairModel(repairDto));
        return ResponseEntity.ok(RepairDto.toRepairDto(repairModel));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RepairDto> update(@PathVariable Long id, @RequestBody RepairDto repairDto){
        RepairModel repairModel = repairService.update(id, RepairModel.toRepairModel(repairDto));
        return ResponseEntity.ok(RepairDto.toRepairDto(repairModel));
    }

    @PutMapping("/{id}/budget")
    public ResponseEntity<RepairDto> addBudget(@PathVariable Long id, @RequestBody List<BudgetDto> budgetDto){
        RepairModel repairModel = repairService.addBudget(id, BudgetModel.toBudgetModel(budgetDto));
        return ResponseEntity.ok(RepairDto.toRepairDto(repairModel));
    }
}
