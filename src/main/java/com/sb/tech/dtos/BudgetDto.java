package com.sb.tech.dtos;

import com.sb.tech.models.HardwareTypeModel;

import java.math.BigDecimal;

public record BudgetDto(
    Long id,
    BigDecimal repairValue,
    String details,
    HardwareTypeDto hardwareType
) {
}
