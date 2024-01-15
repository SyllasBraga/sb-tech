package com.sb.tech.repositories;

import com.sb.tech.models.TechnicianModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TechnicianRepository extends JpaRepository<TechnicianModel, UUID> {
    TechnicianModel findByDocument(String document);
}
