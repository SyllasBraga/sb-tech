package com.sb.tech.dtos;

import com.sb.tech.models.ClientModel;
import com.sb.tech.models.TechnicianModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RepairDto {
    private Long id;
    private Timestamp entryDate;
    private Timestamp repairTimeStipulated;
    private Timestamp outDate;
    private ClientDto client;
    private TechnicianDto technician;
    private String paymentStatus;
}