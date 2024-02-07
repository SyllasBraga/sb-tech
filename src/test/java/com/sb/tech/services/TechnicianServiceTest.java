package com.sb.tech.services;


import com.sb.tech.exceptions.NotFoundException;
import com.sb.tech.exceptions.UuidParseException;
import com.sb.tech.models.TechnicianModel;
import com.sb.tech.repositories.TechnicianRepository;
import com.sb.tech.services.impl.TechnicianServiceImpl;
import org.hibernate.mapping.Any;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.UUID;

import static java.util.Optional.ofNullable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TechnicianServiceTest {

    private static final String TECH_CPF = "40671276000";
    private static final UUID TECH_UUID = UUID.randomUUID();
    public static final String PASSWORD_LOGIN = "12345678";
    private TechnicianModel technicianSaved;
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
        startTechnicianExpected();
        startTechnicianWillBeSave();
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

    @Test
    void whenGetByDocumentReturnsATechnician(){
        when(technicianRepository.findByDocument(TECH_CPF)).thenReturn(technicianExpected);
        TechnicianModel technicianModel = technicianService.getTechnicianByDocument(TECH_CPF);
        assertEquals(TechnicianModel.class, technicianModel.getClass());
    }

    @Test
    void whenInsertReturnsATechnician(){
        when(technicianRepository.save(any())).thenReturn(technicianSaved);
        TechnicianModel technicianModel = technicianService.insertTechnician(technicianSaved);
        assertEquals(TechnicianModel.class, technicianModel.getClass());
    }

    @Test
    void whenUpdateReturnsATechnician(){
        when(technicianRepository.findById(TECH_UUID)).thenReturn(ofNullable(technicianExpected));
        when(technicianRepository.save(any())).thenReturn(technicianExpected);
        TechnicianModel technicianModel = technicianService.updateTechnician(TECH_UUID.toString(), technicianExpected);
        assertEquals(TechnicianModel.class, technicianModel.getClass());
    }

    @Test
    void whenGetByUpdateThrowsNotFoundException(){
        when(technicianRepository.findById(TECH_UUID)).thenThrow(new NotFoundException(null));
        when(technicianRepository.save(any())).thenReturn(technicianExpected);
        try {
            technicianService.updateTechnician(TECH_UUID.toString(), technicianExpected);
        }catch (Exception e){
            assertEquals(NotFoundException.class, e.getClass());
        }
    }

    @Test
    void whenGetByUpdateThrowsUuidParseException(){
        when(technicianRepository.findById(TECH_UUID)).thenThrow(new UuidParseException(null));
        when(technicianRepository.save(any())).thenReturn(technicianExpected);
        try {
            technicianService.updateTechnician(TECH_UUID.toString(), technicianExpected);
        }catch (Exception e){
            assertEquals(UuidParseException.class, e.getClass());
        }
    }

    @Test
    void whenDeleteShouldReturnsOk(){
        doNothing().when(technicianRepository).deleteById(any());
        technicianService.deleteTechnician(TECH_UUID.toString());
        verify(technicianRepository, times(1)).deleteById(TECH_UUID);
    }

    private void startTechnicianExpected(){
        technicianExpected = new TechnicianModel();
        technicianSaved = new TechnicianModel();
        technicianSaved.setId(TECH_UUID);
    }

    private void startTechnicianWillBeSave(){
        technicianSaved = new TechnicianModel();
        technicianSaved.setPasswordLogin(PASSWORD_LOGIN);
    }
}
