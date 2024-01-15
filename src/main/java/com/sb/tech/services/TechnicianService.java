package com.sb.tech.services;

import com.sb.tech.exceptions.NotFoundException;
import com.sb.tech.exceptions.UuidParseException;
import com.sb.tech.models.TechnicianModel;
import com.sb.tech.repositories.TechnicianRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TechnicianService {
    public final TechnicianRepository technicianRepository;

    public TechnicianService(TechnicianRepository technicianRepository) {
        this.technicianRepository = technicianRepository;
    }

    public TechnicianModel getByUuid(String uuid){
        try {
            return technicianRepository.findById(UUID.fromString(uuid))
                    .orElseThrow(() -> new NotFoundException("Technician not found"));
        }catch (IllegalArgumentException ex){
            throw new UuidParseException("Exception Parse UUID: Invalid UUID");
        }
    }

    public TechnicianModel getTechnicianByDocument(String document){
        return technicianRepository.findByDocument(document);
    }

    public TechnicianModel insertTechnician(TechnicianModel technicianModel){
        technicianModel.setId(UUID.randomUUID());
        return technicianRepository.save(technicianModel);
    }

    public TechnicianModel updateTechnician(TechnicianModel newTechnician){
        TechnicianModel actualTechnician = technicianRepository.findById(newTechnician.getId())
                .orElseThrow(() -> new NotFoundException("Technician not found"));
        actualTechnician.update(newTechnician);
        return technicianRepository.save(actualTechnician);
    }

    public void deleteTechnician(String uuid) {
        try {
            technicianRepository.deleteById(UUID.fromString(uuid));
        } catch (IllegalArgumentException ex) {
            throw new UuidParseException("Exception Parse UUID: Invalid UUID");
        }
    }
}
