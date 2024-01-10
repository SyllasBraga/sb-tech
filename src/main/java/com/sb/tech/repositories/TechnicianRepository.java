package com.sb.tech.repositories;

import com.sb.tech.models.TechnicianModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TechnicianRepository extends JpaRepository<TechnicianModel, UUID> {
}
