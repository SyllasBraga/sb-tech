package com.sb.tech.models;

import com.sb.tech.dtos.HardwareTypeDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "hardware_type")
@AllArgsConstructor
@NoArgsConstructor
public class HardwareTypeModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String details;

    public static HardwareTypeModel toHardwareTypeModel(HardwareTypeDto hardwareTypeDto){
        return new HardwareTypeModel(hardwareTypeDto.getId(), hardwareTypeDto.getDetails());
    }
}
