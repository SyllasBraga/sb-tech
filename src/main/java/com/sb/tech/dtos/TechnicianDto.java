package com.sb.tech.dtos;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

public record TechnicianDto(
    UUID id,
    String document,
    String passwordLogin,
    BigDecimal salary,
    String phone,
    String email,
    Timestamp birthDate,
    Timestamp admissionDate,
    Timestamp firedDate,
    String accountStatus
) {
}
