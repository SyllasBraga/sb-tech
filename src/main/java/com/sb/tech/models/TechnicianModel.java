package com.sb.tech.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    public void update(TechnicianModel newTechnician) {
        this.id = newTechnician.getId();
        this.document = newTechnician.getDocument();
        this.passwordLogin = newTechnician.getPasswordLogin();
        this.salary = newTechnician.getSalary();
        this.phone = newTechnician.getPhone();
        this.email = newTechnician.getEmail();
        this.birthDate = newTechnician.getBirthDate();
        this.admissionDate = newTechnician.getAdmissionDate();
        this.firedDate = newTechnician.getFiredDate();
        this.accountStatus = newTechnician.getAccountStatus();
    }
}
