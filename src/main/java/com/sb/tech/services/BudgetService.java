package com.sb.tech.services;

import com.sb.tech.models.BudgetModel;

public interface BudgetService {

    public BudgetModel insert(BudgetModel budgetModel);
    public void update(BudgetModel budgetModel);
}
