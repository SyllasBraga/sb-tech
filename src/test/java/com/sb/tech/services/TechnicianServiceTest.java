package com.sb.tech.services;


import com.sb.tech.exceptions.NotFoundException;
import com.sb.tech.exceptions.UuidParseException;
import com.sb.tech.models.TechnicianModel;
import com.sb.tech.repositories.TechnicianRepository;
import com.sb.tech.services.impl.TechnicianServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.UUID;

import static java.util.Optional.ofNullable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class TechnicianServiceTest {

    private static final String TECH_CPF = "40671276000";
    private static final UUID TECH_UUID = UUID.randomUUID();
    private TechnicianModel technicianExpected;

    @InjectMocks
    private TechnicianServiceImpl technicianService;
    @Mock
    private TechnicianRepository technicianRepository;
    @Spy
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
        startData();
    }

    @Test
    void whenGetByUuidReturnsATechnician(){
        when(technicianRepository.findById(TECH_UUID)).thenReturn(ofNullable(technicianExpected));
        TechnicianModel technicianModel = technicianService.getByUuid(TECH_UUID.toString());
        assertEquals(TechnicianModel.class, technicianModel.getClass());
    }

    @Test
    void whenGetByUuidThrowsNotFoundException(){
        when(technicianRepository.findById(TECH_UUID)).thenThrow(new NotFoundException(null));
        try {
            technicianService.getByUuid(TECH_UUID.toString());
        }catch (Exception e){
            assertEquals(NotFoundException.class, e.getClass());
        }
    }

    @Test
    void whenGetByUuidThrowsUuidParseException(){
        when(technicianRepository.findById(TECH_UUID)).thenThrow(new UuidParseException(null));
        try {
            technicianService.getByUuid(TECH_UUID.toString());
        }catch (Exception e){
            assertEquals(UuidParseException.class, e.getClass());
        }
    }

    private void startData(){
        technicianExpected = new TechnicianModel();
        technicianExpected.setId(TECH_UUID);
    }
}
