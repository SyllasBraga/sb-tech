package com.sb.tech.api;

import com.sb.tech.dtos.BudgetDto;
import com.sb.tech.dtos.RepairDto;
import jakarta.validation.Valid;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/v1/repair")
public interface RepairApi {

    @GetMapping
    ResponseEntity<List<RepairDto>> getAll();
    @GetMapping("/client")
    ResponseEntity<List<RepairDto>> getByClientDocument(@Valid @RequestParam(name = "document") @CPF String document);
    @GetMapping("/technician")
    ResponseEntity<List<RepairDto>> getByTechnicianUuid(@RequestParam(name = "id") String id);
    @PostMapping
    ResponseEntity<RepairDto> insert(@RequestBody RepairDto repairDto);
    @PutMapping("/{id}")
    ResponseEntity<RepairDto> update(@PathVariable Long id, @RequestBody RepairDto repairDto);
    @PutMapping("/{id}/budget")
    ResponseEntity<RepairDto> addBudget(@PathVariable Long id, @RequestBody BudgetDto budgetDto);
    @DeleteMapping("/{id}")
    ResponseEntity<RepairDto> delete(@PathVariable Long id);
}
