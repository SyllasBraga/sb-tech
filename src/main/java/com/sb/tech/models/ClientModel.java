package com.sb.tech.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "client")
public class ClientModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID id;
    private String document;
    private String name;
    private String phone;
    private String email;

    public void update(ClientModel clientModel) {
        this.id = clientModel.getId();
        this.document = clientModel.getDocument();
        this.name = clientModel.getName();
        this.phone = clientModel.getPhone();
        this.email = clientModel.getEmail();
    }
}
