package com.sb.tech.dtos;

import com.sb.tech.models.ClientModel;
import com.sb.tech.models.TechnicianModel;

import java.sql.Timestamp;

public record RepairDto(
    Long id,
    Timestamp entryDate,
    Timestamp repairTimeStipulated,
    Timestamp outDate,
    ClientDto client,
    TechnicianDto technician,
    String paymentStatus
) {
}
