package com.sb.tech.dtos;

import com.sb.tech.models.BudgetModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BudgetDto{
    private Long id;
    private BigDecimal repairValue;
    private String details;
    private HardwareTypeDto hardwareType;

    public static List<BudgetDto> toBudgetDto(List<BudgetModel> listBudgetModel) {
        List<BudgetDto> listDto = new ArrayList<>();
        listBudgetModel.forEach(budgetModel -> listDto.add(new BudgetDto(budgetModel.getId(), budgetModel.getRepairValue(), budgetModel.getDetails(),
                HardwareTypeDto.toHardwareTypeDto(budgetModel.getHardwareType()))));
        return listDto;
    }
}
