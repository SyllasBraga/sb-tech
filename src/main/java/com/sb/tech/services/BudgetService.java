package com.sb.tech.services;

import com.sb.tech.models.BudgetModel;

public interface BudgetService {

    BudgetModel insert(BudgetModel budgetModel);
    void update(BudgetModel budgetModel);
}
