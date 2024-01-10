package com.sb.tech.repositories;

import com.sb.tech.models.BudgetModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BudgetRepository extends JpaRepository<BudgetModel, Long> {
}
