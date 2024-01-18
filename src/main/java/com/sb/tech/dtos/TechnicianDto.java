package com.sb.tech.dtos;

import com.sb.tech.models.TechnicianModel;
import com.sb.tech.models.enums.AccountStatusEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TechnicianDto {
    private UUID id;
    @CPF(message = "Invalid document.")
    private String document;
    @NotBlank(message = "Field 'passworLogin' cannot be blank.")
    @Size(min = 8, max = 255)
    private String passwordLogin;
    @NotBlank(message = "Field 'name' cannot be blank.")
    @Size(min = 3, max = 255)
    private String name;
    @NotNull(message = "Field 'salary' cannot be null.")
    private BigDecimal salary;
    @NotBlank(message = "Field 'phone' cannot be blank.")
    private String phone;
    @Email(message = "Invalid email.")
    @NotBlank(message = "Field 'email' cannot be blank.")
    private String email;
    private Timestamp birthDate;
    private Timestamp admissionDate;
    private Timestamp firedDate;
    private AccountStatusEnum accountStatus;

    public static TechnicianDto toTechnicianDto(TechnicianModel technicianModel) {
        return new TechnicianDto(technicianModel.getId(), technicianModel.getDocument(), technicianModel.getPasswordLogin(),
                technicianModel.getName(), technicianModel.getSalary(), technicianModel.getPhone(), technicianModel.getEmail(),
                technicianModel.getBirthDate(), technicianModel.getAdmissionDate(), technicianModel.getFiredDate(),
                technicianModel.getAccountStatus());
    }
}