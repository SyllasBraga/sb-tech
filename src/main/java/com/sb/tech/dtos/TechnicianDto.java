package com.sb.tech.dtos;

import com.sb.tech.models.TechnicianModel;
import com.sb.tech.models.enums.AccountStatusEnum;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

public record TechnicianDto(
    UUID id,
    String document,
    String passwordLogin,
    String name,
    BigDecimal salary,
    String phone,
    String email,
    Timestamp birthDate,
    Timestamp admissionDate,
    Timestamp firedDate,
    AccountStatusEnum accountStatus
) {
    public TechnicianDto {
    }

    public static TechnicianDto toTechnicianDto(TechnicianModel technicianModel) {
        return new TechnicianDto(technicianModel.getId(), technicianModel.getDocument(), technicianModel.getPasswordLogin(),
                technicianModel.getName(), technicianModel.getSalary(), technicianModel.getPhone(), technicianModel.getEmail(),
                technicianModel.getBirthDate(), technicianModel.getAdmissionDate(), technicianModel.getFiredDate(),
                technicianModel.getAccountStatus());
    }
}
