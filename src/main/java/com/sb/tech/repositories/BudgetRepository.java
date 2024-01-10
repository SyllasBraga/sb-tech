package com.sb.tech.repositories;

import com.sb.tech.models.BudgetModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BudgetRepository extends JpaRepository<BudgetModel, Long> {
}
