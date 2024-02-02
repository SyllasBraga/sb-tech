package com.sb.tech.api;

import com.sb.tech.dtos.BudgetDto;
import com.sb.tech.dtos.RepairDto;
import jakarta.validation.Valid;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface RepairApi {

    @GetMapping
    public ResponseEntity<List<RepairDto>> getAll();
    @GetMapping("/client")
    public ResponseEntity<List<RepairDto>> getByClientDocument(@Valid @RequestParam(name = "document") @CPF String document);
    @GetMapping("/technician")
    public ResponseEntity<List<RepairDto>> getByTechnicianUuid(@RequestParam(name = "id") String id);
    @PostMapping
    public ResponseEntity<RepairDto> insert(@RequestBody RepairDto repairDto);
    @PutMapping("/{id}")
    public ResponseEntity<RepairDto> update(@PathVariable Long id, @RequestBody RepairDto repairDto);
    @PutMapping("/{id}/budget")
    public ResponseEntity<RepairDto> addBudget(@PathVariable Long id, @RequestBody BudgetDto budgetDto);
    @DeleteMapping("/{id}")
    public ResponseEntity<RepairDto> delete(@PathVariable Long id);
}
