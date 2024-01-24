package com.sb.tech.services;

import com.sb.tech.exceptions.NotFoundException;
import com.sb.tech.models.BudgetModel;
import com.sb.tech.repositories.BudgetRepository;
import org.springframework.stereotype.Service;

@Service
public class BudgetService {

    private final BudgetRepository budgetRepository;
    private static final String BUDGET_NOT_FOUND = "Budget not found";

    public BudgetService(BudgetRepository budgetRepository) {
        this.budgetRepository = budgetRepository;
    }

    public void insert(BudgetModel budgetModel){
        budgetRepository.save(budgetModel);
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
