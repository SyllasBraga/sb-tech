package com.sb.tech.services;

import com.sb.tech.models.BudgetModel;
import com.sb.tech.models.RepairModel;

import java.util.List;

public interface RepairService {

    List<RepairModel> getAll();
    List<RepairModel> getByClientDocument(String document);
    List<RepairModel> getByTechnicianId(String uuid);
    RepairModel insert(RepairModel repairModel);
    RepairModel update(Long id, RepairModel repairModel);
    void delete(Long id);
    RepairModel addBudget(Long id, BudgetModel budgetModel);
}
