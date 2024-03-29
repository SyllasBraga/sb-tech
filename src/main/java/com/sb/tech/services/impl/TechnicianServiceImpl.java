package com.sb.tech.services.impl;

import com.sb.tech.exceptions.NotFoundException;
import com.sb.tech.exceptions.UuidParseException;
import com.sb.tech.models.TechnicianModel;
import com.sb.tech.models.enums.AccountStatusEnum;
import com.sb.tech.models.enums.Role;
import com.sb.tech.repositories.TechnicianRepository;
import com.sb.tech.services.TechnicianService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TechnicianServiceImpl implements TechnicianService {
    private final TechnicianRepository technicianRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private static final String EXCEPTION_UUID_INVALID = "Exception Parse UUID: Invalid UUID";
    private static final String TECHNICIAN_NOT_FOUND = "Technician not found";

    public TechnicianServiceImpl(TechnicianRepository technicianRepository, BCryptPasswordEncoder passwordEncoder) {
        this.technicianRepository = technicianRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public TechnicianModel getByUuid(String uuid){
        try {
            return technicianRepository.findById(UUID.fromString(uuid))
                    .orElseThrow(() -> new NotFoundException(TECHNICIAN_NOT_FOUND));
        }catch (IllegalArgumentException ex){
            throw new UuidParseException(EXCEPTION_UUID_INVALID);
        }
    }

    public TechnicianModel getTechnicianByDocument(String document){
        TechnicianModel technicianModel = technicianRepository.findByDocument(document);
        if(technicianModel == null){
            throw new NotFoundException(TECHNICIAN_NOT_FOUND);
        }
        return technicianModel;
    }

    public TechnicianModel insertTechnician(TechnicianModel technicianModel){
        technicianModel.setId(UUID.randomUUID());
        technicianModel.setPasswordLogin(passwordEncoder.encode(technicianModel.getPasswordLogin()));
        technicianModel.setAccountStatus(AccountStatusEnum.ACTIVE);
        technicianModel.setRole(Role.TECHNICIAN);
        return technicianRepository.save(technicianModel);
    }

    public TechnicianModel updateTechnician(String id, TechnicianModel newTechnician){
        try {
            newTechnician.setId(UUID.fromString(id));
            newTechnician.setAccountStatus(AccountStatusEnum.ACTIVE);
            TechnicianModel actualTechnician = getByUuid(id);
            actualTechnician.update(newTechnician);
            return technicianRepository.save(actualTechnician);
        }catch (IllegalArgumentException ex){
            throw new UuidParseException(EXCEPTION_UUID_INVALID);
        }
    }

    public void deleteTechnician(String uuid) {
        try {
            technicianRepository.deleteById(UUID.fromString(uuid));
        } catch (IllegalArgumentException ex) {
            throw new UuidParseException(EXCEPTION_UUID_INVALID);
        }
    }

    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return getTechnicianByDocument(username);
            }
        };
    }
}
