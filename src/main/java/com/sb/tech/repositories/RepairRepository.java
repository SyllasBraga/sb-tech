package com.sb.tech.repositories;

import com.sb.tech.models.RepairModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepairRepository extends JpaRepository<RepairModel, Long> {
}
