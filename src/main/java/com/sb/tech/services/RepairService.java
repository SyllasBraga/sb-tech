package com.sb.tech.services;

import com.sb.tech.models.BudgetModel;
import com.sb.tech.models.RepairModel;

import java.util.List;

public interface RepairService {

    public List<RepairModel> getAll();
    public List<RepairModel> getByClientDocument(String document);
    public List<RepairModel> getByTechnicianId(String uuid);
    public RepairModel insert(RepairModel repairModel);
    public RepairModel update(Long id, RepairModel repairModel);
    public void delete(Long id);
    public RepairModel addBudget(Long id, BudgetModel budgetModel);
}
