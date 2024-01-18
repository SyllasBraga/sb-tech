package com.sb.tech.models;

import com.sb.tech.dtos.TechnicianDto;
import com.sb.tech.models.enums.AccountStatusEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "technician")
@Entity
public class TechnicianModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID id;
    private String document;
    @Column(name = "password_login")
    private String passwordLogin;
    private String name;
    private BigDecimal salary;
    private String phone;
    private String email;
    @Column(name = "birth_date")
    private Timestamp birthDate;
    @Column(name = "admission_date")
    private Timestamp admissionDate;
    @Column(name = "fired_date")
    private Timestamp firedDate;
    @Column(name = "id_account_status")
    @Enumerated(EnumType.STRING)
    private AccountStatusEnum accountStatus;

    public void update(TechnicianModel newTechnician) {
        this.id = newTechnician.getId();
        this.document = newTechnician.getDocument();
        this.name = newTechnician.getName();
        this.passwordLogin = newTechnician.getPasswordLogin();
        this.salary = newTechnician.getSalary();
        this.phone = newTechnician.getPhone();
        this.email = newTechnician.getEmail();
        this.birthDate = newTechnician.getBirthDate();
        this.admissionDate = newTechnician.getAdmissionDate();
        this.firedDate = newTechnician.getFiredDate();
        this.accountStatus = newTechnician.getAccountStatus();
    }

    public static TechnicianModel toTechnicianModel(TechnicianDto technicianDto) {
        return new TechnicianModel(technicianDto.getId(), technicianDto.getDocument(), technicianDto.getPasswordLogin(),
                technicianDto.getName(), technicianDto.getSalary(), technicianDto.getPhone(), technicianDto.getEmail(),
                technicianDto.getBirthDate(), technicianDto.getAdmissionDate(), technicianDto.getFiredDate(),
                technicianDto.getAccountStatus());
    }
}
