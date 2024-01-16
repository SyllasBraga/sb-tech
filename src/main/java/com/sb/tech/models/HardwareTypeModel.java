package com.sb.tech.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "hardware_type")
public class HardwareTypeModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String details;
}
