package com.sb.tech.services;

import com.sb.tech.exceptions.NotFoundException;
import com.sb.tech.exceptions.UuidParseException;
import com.sb.tech.models.BudgetModel;
import com.sb.tech.models.RepairModel;
import com.sb.tech.repositories.RepairRepository;

import java.util.List;
import java.util.UUID;

public class RepairService {

    private static final String EXCEPTION_UUID_INVALID = "Exception Parse UUID: Invalid UUID";
    private final RepairRepository repository;
    private final BudgetService budgetService;

    public RepairService(RepairRepository repository, BudgetService budgetService) {
        this.repository = repository;
        this.budgetService = budgetService;
    }

    public List<RepairModel> getAll(){
        return repository.findAll();
    }

    public RepairModel getByClientDocument(String document){
        return repository.findByClientDocument(document);
    }

    public RepairModel getByTechnicianId(String uuid){
        try {
            return repository.findByTechnicianUuid(UUID.fromString(uuid));
        }catch (IllegalArgumentException ex){
            throw new UuidParseException(EXCEPTION_UUID_INVALID);
        }
    }

    public RepairModel insert(RepairModel repairModel){
        return repository.save(repairModel);
    }

    public RepairModel update(Long id, RepairModel repairModel){
        RepairModel actualRepair = repository.findById(id).orElseThrow(()-> new NotFoundException("Repair not found"));
        actualRepair.update(repairModel);
        return repository.save(actualRepair);
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

    public RepairModel addBudget(Long id, BudgetModel budgetModel){
        RepairModel actualRepair = repository.findById(id).orElseThrow(()-> new NotFoundException("Repair not found"));
        actualRepair.getBudgetList().add(budgetModel);
        return repository.save(actualRepair);
    }
}
