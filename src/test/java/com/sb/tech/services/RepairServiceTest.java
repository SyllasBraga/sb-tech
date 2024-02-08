package com.sb.tech.services;

import com.sb.tech.exceptions.NotFoundException;
import com.sb.tech.models.BudgetModel;
import com.sb.tech.models.RepairModel;
import com.sb.tech.repositories.RepairRepository;
import com.sb.tech.services.impl.BudgetServiceImpl;
import com.sb.tech.services.impl.RepairServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class RepairServiceTest {

    private RepairModel repair;
    private BudgetModel budget;
    private List<RepairModel> repairList;
    private static final UUID CLIENT_UUID = UUID.randomUUID();
    private static final String CLIENT_DOCUMENT = "12345678";
    private static final Long REPAIR_ID = 1234L;

    @Mock
    private RepairRepository repository;
    @Mock
    private BudgetServiceImpl budgetService;
    @InjectMocks
    private RepairServiceImpl service;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        startListRepairs();
        startRepair();
        startBudget();
    }

    @Test
    void whenGetAllReturnsAListOfRepairs() {
        when(repository.findAll()).thenReturn(repairList);
        List<RepairModel> result = service.getAll();
        assertEquals(2, result.size());
        assertEquals(RepairModel.class, result.get(0).getClass());
    }

    @Test
    void whenGetByClientDocumentReturnsAListOfRepairs() {
        when(repository.findByClientDocument(CLIENT_DOCUMENT)).thenReturn(repairList);
        List<RepairModel> result = service.getByClientDocument(CLIENT_DOCUMENT);
        assertEquals(2, result.size());
        assertEquals(RepairModel.class, result.get(0).getClass());
    }

    @Test
    void whenGetByTechnicianIdReturnsAListOfRepairs() {
        when(repository.findByTechnicianUuid(CLIENT_UUID.toString())).thenReturn(repairList);
        List<RepairModel> result = service.getByTechnicianId(CLIENT_UUID.toString());
        assertEquals(2, result.size());
        assertEquals(RepairModel.class, result.get(0).getClass());
    }

    @Test
    void whenInsertReturnsARepair() {
        when(repository.save(repair)).thenReturn(repair);
        RepairModel result = service.insert(repair);
        assertNotNull(result);
        assertEquals(RepairModel.class, result.getClass());
    }

    @Test
    void whenUpdateReturnsARepair() {
        when(repository.findById(anyLong())).thenReturn(Optional.of(repair));
        when(repository.save(any())).thenReturn(repair);
        RepairModel result = service.update(REPAIR_ID, repair);
        assertNotNull(result);
        assertEquals(RepairModel.class, result.getClass());
    }

    @Test
    void whenUpdateThrowsNotFoundException() {
        when(repository.findById(REPAIR_ID)).thenThrow(new NotFoundException(null));
        try {
            service.update(REPAIR_ID, repair);
        }catch (RuntimeException ex){
            assertEquals(NotFoundException.class, ex.getClass());
        }
    }

    @Test
    void whenDeleteReturnsOk() {
        when(repository.findById(anyLong())).thenReturn(Optional.of(repair));
        doNothing().when(repository).deleteById(anyLong());
        service.delete(REPAIR_ID);
        verify(repository, times(1)).deleteById(REPAIR_ID);
    }

    @Test
    void whenDeleteThrowsNotFoundException() {
        when(repository.findById(anyLong())).thenThrow(new NotFoundException(null));
        try {
            service.delete(REPAIR_ID);
        }catch (RuntimeException ex){
            assertEquals(NotFoundException.class, ex.getClass());
        }
    }

    @Test
    void whenAddBudgetReturnsARepair() {
        when(repository.findById(any())).thenReturn(Optional.of(repair));
        when(budgetService.insert(any())).thenReturn(budget);
        when(repository.save(any())).thenReturn(repair);
        RepairModel result = service.addBudget(REPAIR_ID, budget);
        assertNotNull(result);
        assertEquals(1, result.getBudgetList().size());
        assertEquals(RepairModel.class, result.getClass());
    }

    private void startListRepairs(){
        repairList = new ArrayList<>();
        repairList.add(new RepairModel());
        repairList.add(new RepairModel());
    }

    private void startRepair() {
        repair = new RepairModel();
        repair.setId(REPAIR_ID);
        repair.setBudgetList(new ArrayList<>());
    }

    private void startBudget(){
        budget = new BudgetModel();
    }
}
