package com.sb.tech.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "repair")
public class RepairModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Timestamp entryDate;
    private Timestamp repairTimeStipulated;
    private Timestamp outDate;
    private ClientModel idClient;
    private TechnicianModel idTechnician;
    private String paymentStatus;
}
