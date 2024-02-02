package com.sb.tech.services;

import com.sb.tech.models.TechnicianModel;

public interface TechnicianService {

    public TechnicianModel getByUuid(String uuid);
    public TechnicianModel getTechnicianByDocument(String document);
    public TechnicianModel insertTechnician(TechnicianModel technicianModel);
    public TechnicianModel updateTechnician(String id, TechnicianModel newTechnician);
    public void deleteTechnician(String uuid);
}
