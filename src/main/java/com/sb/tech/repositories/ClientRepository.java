package com.sb.tech.repositories;

import com.sb.tech.models.ClientModel;
import com.sb.tech.models.TechnicianModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<ClientModel, UUID> {
    ClientModel findByDocument(String document);
}
