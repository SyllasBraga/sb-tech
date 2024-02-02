package com.sb.tech.controllers;

import com.sb.tech.api.RepairApi;
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
public class RepairController implements RepairApi {

    private final RepairService repairService;

    public RepairController(RepairService repairService) {
        this.repairService = repairService;
    }

    @Override
    public ResponseEntity<List<RepairDto>> getAll(){
        List<RepairDto> listDto = new ArrayList<>();
        List<RepairModel> listModel = repairService.getAll();
        listModel.forEach(repairModel -> listDto.add(RepairDto.toRepairDto(repairModel)));
        return ResponseEntity.ok(listDto);
    }

    @Override
    public ResponseEntity<List<RepairDto>> getByClientDocument(@Valid @RequestParam(name = "document") @CPF String document){
        List<RepairDto> repairDto = new ArrayList<>();
        repairService.getByClientDocument(document)
                .forEach(repairModel -> repairDto.add(RepairDto.toRepairDto(repairModel)));
        return ResponseEntity.ok(repairDto);
    }

    @Override
    public ResponseEntity<List<RepairDto>> getByTechnicianUuid(@RequestParam(name = "id") String id){
        List<RepairDto> repairDto = new ArrayList<>();
        repairService.getByTechnicianId(id)
                .forEach(repairModel -> repairDto.add(RepairDto.toRepairDto(repairModel)));
        return ResponseEntity.ok(repairDto);
    }

    @Override
    public ResponseEntity<RepairDto> insert(@RequestBody RepairDto repairDto){
        RepairModel repairModel = repairService.insert(RepairModel.toRepairModel(repairDto));
        return ResponseEntity.ok(RepairDto.toRepairDto(repairModel));
    }

    @Override
    public ResponseEntity<RepairDto> update(@PathVariable Long id, @RequestBody RepairDto repairDto){
        RepairModel repairModel = repairService.update(id, RepairModel.toRepairModel(repairDto));
        return ResponseEntity.ok(RepairDto.toRepairDto(repairModel));
    }

    @Override
    public ResponseEntity<RepairDto> addBudget(@PathVariable Long id, @RequestBody BudgetDto budgetDto){
        RepairModel repairModel = repairService.addBudget(id, BudgetModel.toBudgetModel(budgetDto));
        return ResponseEntity.ok(RepairDto.toRepairDto(repairModel));
    }

    @Override
    public ResponseEntity<RepairDto> delete(@PathVariable Long id){
        repairService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
