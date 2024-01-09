package com.sb.tech.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TechnicianModel {
    private UUID id;
    private String document;
    private String passwordLogin;
    private BigDecimal salary;
    private String phone;
    private String email;
    private Timestamp birthDate;
    private Timestamp admissionDate;
    private Timestamp firedDate;
    private String accountStatus;
}
