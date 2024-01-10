package com.sb.tech.repositories;

import com.sb.tech.models.HardwareTypeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HardwareTypeRepository extends JpaRepository<HardwareTypeModel, Long> {
}
