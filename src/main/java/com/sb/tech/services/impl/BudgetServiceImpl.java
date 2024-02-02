package com.sb.tech.services.impl;

import com.sb.tech.exceptions.NotFoundException;
import com.sb.tech.models.BudgetModel;
import com.sb.tech.repositories.BudgetRepository;
import com.sb.tech.services.BudgetService;
import org.springframework.stereotype.Service;

@Service
public class BudgetServiceImpl implements BudgetService {

    private final BudgetRepository budgetRepository;
    private static final String BUDGET_NOT_FOUND = "Budget not found";

    public BudgetServiceImpl(BudgetRepository budgetRepository) {
        this.budgetRepository = budgetRepository;
    }

    public BudgetModel insert(BudgetModel budgetModel){
        return budgetRepository.save(budgetModel);
    }

    public void update(BudgetModel budgetModel){
        BudgetModel actualBudgetModel = getById(budgetModel.getId());
        actualBudgetModel.update(budgetModel);
        budgetRepository.save(actualBudgetModel);
    }

    private BudgetModel getById(Long id){
        return budgetRepository.findById(id).orElseThrow(()-> new NotFoundException(BUDGET_NOT_FOUND));
    }
}
