package com.sb.tech.services;

import com.sb.tech.models.BudgetModel;
import com.sb.tech.repositories.BudgetRepository;
import com.sb.tech.services.impl.BudgetServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class BudgetServiceTest {

    private BudgetModel budgetModel;
    private static final Long BUDGET_ID = 123L;
    @InjectMocks
    private BudgetServiceImpl budgetService;
    @Mock
    private BudgetRepository budgetRepository;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
        startBudget();
    }

    @Test
    void whenInsertReturnsABudget(){
        when(budgetRepository.save(any())).thenReturn(budgetModel);
        BudgetModel result = budgetService.insert(budgetModel);
        assertNotNull(result);
        assertEquals(BudgetModel.class, result.getClass());
    }


    @Test
    void whenUpdateReturnsABudget(){
        when(budgetRepository.findById(BUDGET_ID)).thenReturn(Optional.of(budgetModel));
        when(budgetRepository.save(any(BudgetModel.class))).thenReturn(budgetModel);
        budgetService.insert(budgetModel);
        verify(budgetRepository, times(1)).save(budgetModel);
    }

    private void startBudget(){
        budgetModel = new BudgetModel();
    }
}
