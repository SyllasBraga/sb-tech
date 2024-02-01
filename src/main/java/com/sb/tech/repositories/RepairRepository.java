package com.sb.tech.repositories;

import com.sb.tech.models.RepairModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RepairRepository extends JpaRepository<RepairModel, Long> {

    @Query(value = "SELECT  rep.*, c.id as client_id FROM `repair` rep " +
            "INNER JOIN client c ON rep.id_client = c.id " +
            "WHERE c.document = :document", nativeQuery = true)
    List<RepairModel> findByClientDocument(String document);

    @Query(value = "SELECT r.*, t.id as tech_id FROM `repair` r " +
            "INNER JOIN technician t ON t.id = r.id_technician " +
            "WHERE t.id = :uuid", nativeQuery = true)
    List<RepairModel> findByTechnicianUuid(String uuid);
}
