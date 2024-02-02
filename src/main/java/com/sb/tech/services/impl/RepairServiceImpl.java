package com.sb.tech.services.impl;

import com.sb.tech.exceptions.NotFoundException;
import com.sb.tech.models.BudgetModel;
import com.sb.tech.models.RepairModel;
import com.sb.tech.repositories.RepairRepository;
import com.sb.tech.services.RepairService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepairServiceImpl implements RepairService {

    public static final String REPAIR_NOT_FOUND = "Repair not found";
    private final RepairRepository repository;
    private final BudgetServiceImpl budgetServiceImpl;

    public RepairServiceImpl(RepairRepository repository, BudgetServiceImpl budgetServiceImpl) {
        this.repository = repository;
        this.budgetServiceImpl = budgetServiceImpl;
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

    public RepairModel addBudget(Long id, BudgetModel budgetModel){
        RepairModel actualRepair = repository.findById(id).orElseThrow(()-> new NotFoundException(REPAIR_NOT_FOUND));
        BudgetModel budgetModelSaved = budgetServiceImpl.insert(budgetModel);
        actualRepair.getBudgetList().add(budgetModelSaved);
        return repository.save(actualRepair);
    }
}
