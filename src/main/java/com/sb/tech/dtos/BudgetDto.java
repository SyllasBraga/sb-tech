package com.sb.tech.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BudgetDto{
    private Long id;
    private BigDecimal repairValue;
    private String details;
    private HardwareTypeDto hardwareType;
}
