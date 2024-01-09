package com.sb.tech.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BudgetModel {
    private Long id;
    private BigDecimal repairValue;
    private String details;
    private HardwareTypeModel hardwareType;
}
