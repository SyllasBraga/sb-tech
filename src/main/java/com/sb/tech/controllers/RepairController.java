package com.sb.tech.controllers;

import com.sb.tech.api.RepairApi;
import com.sb.tech.dtos.BudgetDto;
import com.sb.tech.dtos.RepairDto;
import com.sb.tech.models.BudgetModel;
import com.sb.tech.models.RepairModel;
import com.sb.tech.services.impl.RepairServiceImpl;
import jakarta.validation.Valid;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RepairController implements RepairApi {

    private final RepairServiceImpl repairServiceImpl;

    public RepairController(RepairServiceImpl repairServiceImpl) {
        this.repairServiceImpl = repairServiceImpl;
    }

    @Override
    public ResponseEntity<List<RepairDto>> getAll(){
        List<RepairDto> listDto = new ArrayList<>();
        List<RepairModel> listModel = repairServiceImpl.getAll();
        listModel.forEach(repairModel -> listDto.add(RepairDto.toRepairDto(repairModel)));
        return ResponseEntity.ok(listDto);
    }

    @Override
    public ResponseEntity<List<RepairDto>> getByClientDocument(@Valid @RequestParam(name = "document") @CPF String document){
        List<RepairDto> repairDto = new ArrayList<>();
        repairServiceImpl.getByClientDocument(document)
                .forEach(repairModel -> repairDto.add(RepairDto.toRepairDto(repairModel)));
        return ResponseEntity.ok(repairDto);
    }

    @Override
    public ResponseEntity<List<RepairDto>> getByTechnicianUuid(@RequestParam(name = "id") String id){
        List<RepairDto> repairDto = new ArrayList<>();
        repairServiceImpl.getByTechnicianId(id)
                .forEach(repairModel -> repairDto.add(RepairDto.toRepairDto(repairModel)));
        return ResponseEntity.ok(repairDto);
    }

    @Override
    public ResponseEntity<RepairDto> insert(@RequestBody RepairDto repairDto){
        RepairModel repairModel = repairServiceImpl.insert(RepairModel.toRepairModel(repairDto));
        return ResponseEntity.ok(RepairDto.toRepairDto(repairModel));
    }

    @Override
    public ResponseEntity<RepairDto> update(@PathVariable Long id, @RequestBody RepairDto repairDto){
        RepairModel repairModel = repairServiceImpl.update(id, RepairModel.toRepairModel(repairDto));
        return ResponseEntity.ok(RepairDto.toRepairDto(repairModel));
    }

    @Override
    public ResponseEntity<RepairDto> addBudget(@PathVariable Long id, @RequestBody BudgetDto budgetDto){
        RepairModel repairModel = repairServiceImpl.addBudget(id, BudgetModel.toBudgetModel(budgetDto));
        return ResponseEntity.ok(RepairDto.toRepairDto(repairModel));
    }

    @Override
    public ResponseEntity<RepairDto> delete(@PathVariable Long id){
        repairServiceImpl.delete(id);
        return ResponseEntity.noContent().build();
    }
}
