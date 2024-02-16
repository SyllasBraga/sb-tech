package com.sb.tech.services;

import com.sb.tech.models.BudgetModel;
import com.sb.tech.models.RepairModel;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

public interface RepairService {

    @Cacheable("repairs")
    List<RepairModel> getAll();
    List<RepairModel> getByClientDocument(String document);
    List<RepairModel> getByTechnicianId(String uuid);
    @CachePut("repairs")
    RepairModel insert(RepairModel repairModel);
    @CachePut("repairs")
    RepairModel update(Long id, RepairModel repairModel);
    @CacheEvict("repairs")
    void delete(Long id);
    @CachePut("repairs")
    RepairModel addBudget(Long id, BudgetModel budgetModel);
}
