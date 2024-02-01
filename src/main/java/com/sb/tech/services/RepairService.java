package com.sb.tech.services;

import com.sb.tech.exceptions.NotFoundException;
import com.sb.tech.exceptions.UuidParseException;
import com.sb.tech.models.BudgetModel;
import com.sb.tech.models.RepairModel;
import com.sb.tech.repositories.RepairRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RepairService {

    private static final String EXCEPTION_UUID_INVALID = "Exception Parse UUID: Invalid UUID";
    public static final String REPAIR_NOT_FOUND = "Repair not found";
    private final RepairRepository repository;
    private final BudgetService budgetService;

    public RepairService(RepairRepository repository, BudgetService budgetService) {
        this.repository = repository;
        this.budgetService = budgetService;
    }

    public List<RepairModel> getAll(){
        return repository.findAll();
    }

    public List<RepairModel> getByClientDocument(String document){
        return repository.findByClientDocument(document);
    }

    public List<RepairModel> getByTechnicianId(String uuid){
        return repository.findByTechnicianUuid(uuid);
    }

    public RepairModel insert(RepairModel repairModel){
        return repository.save(repairModel);
    }

    public RepairModel update(Long id, RepairModel repairModel){
        RepairModel actualRepair = repository.findById(id).orElseThrow(()-> new NotFoundException(REPAIR_NOT_FOUND));
        actualRepair.update(repairModel);
        return repository.save(actualRepair);
    }

    public void delete(Long id){
        RepairModel repairModel = repository.findById(id).orElseThrow(()-> new NotFoundException(REPAIR_NOT_FOUND));
        repository.deleteById(repairModel.getId());
    }

    public RepairModel addBudget(Long id, List<BudgetModel> budgetModel){
        RepairModel actualRepair = repository.findById(id).orElseThrow(()-> new NotFoundException(REPAIR_NOT_FOUND));
        budgetModel.forEach(newBudgetModel -> actualRepair.getBudgetList().add(newBudgetModel));
        return repository.save(actualRepair);
    }
}
