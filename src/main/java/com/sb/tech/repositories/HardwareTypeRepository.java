package com.sb.tech.repositories;

import com.sb.tech.models.HardwareTypeModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HardwareTypeRepository extends JpaRepository<HardwareTypeModel, Long> {
}
