package com.sb.tech.models;

import com.sb.tech.dtos.TechnicianDto;
import com.sb.tech.models.enums.AccountStatusEnum;
import com.sb.tech.models.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "technician")
@Entity
@Builder
public class TechnicianModel implements UserDetails {
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
    @Enumerated(EnumType.STRING)
    @Column(name = "role_type")
    private Role role;

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
                technicianDto.getAccountStatus(), Role.TECHNICIAN);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.role.getRoleType()));
    }

    @Override
    public String getPassword() {
        return this.passwordLogin;
    }

    @Override
    public String getUsername() {
        return this.document;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
