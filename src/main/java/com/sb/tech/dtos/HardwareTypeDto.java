package com.sb.tech.dtos;

import com.sb.tech.models.HardwareTypeModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HardwareTypeDto{
    private Long id;
    private String details;

    public static HardwareTypeDto toHardwareTypeDto(HardwareTypeModel hardwareTypeModel){
        return new HardwareTypeDto(hardwareTypeModel.getId(), hardwareTypeModel.getDetails());
    }
}
