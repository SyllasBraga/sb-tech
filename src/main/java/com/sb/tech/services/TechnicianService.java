package com.sb.tech.services;

import com.sb.tech.exceptions.NotFoundException;
import com.sb.tech.exceptions.UuidParseException;
import com.sb.tech.models.TechnicianModel;
import com.sb.tech.models.enums.AccountStatusEnum;
import com.sb.tech.repositories.TechnicianRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TechnicianService {
    private final TechnicianRepository technicianRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private static final String EXCEPTION_UUID_INVALID = "Exception Parse UUID: Invalid UUID";
    private static final String TECHNICIAN_NOT_FOUND = "Technician not found";

    public TechnicianService(TechnicianRepository technicianRepository, BCryptPasswordEncoder passwordEncoder) {
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
        return technicianRepository.findByDocument(document);
    }

    public TechnicianModel insertTechnician(TechnicianModel technicianModel){
        technicianModel.setId(UUID.randomUUID());
        technicianModel.setPasswordLogin(passwordEncoder.encode(technicianModel.getPasswordLogin()));
        technicianModel.setAccountStatus(AccountStatusEnum.ACTIVE);
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
}
