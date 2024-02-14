package com.sb.tech.services;

import com.sb.tech.models.TechnicianModel;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface TechnicianService {

    TechnicianModel getByUuid(String uuid);
    TechnicianModel getTechnicianByDocument(String document);
    TechnicianModel insertTechnician(TechnicianModel technicianModel);
    TechnicianModel updateTechnician(String id, TechnicianModel newTechnician);
    void deleteTechnician(String uuid);
    UserDetailsService userDetailsService();
}
