package com.sb.tech.models;

import com.sb.tech.dtos.BudgetDto;
import com.sb.tech.dtos.HardwareTypeDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "budget")
public class BudgetModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "repair_value")
    private BigDecimal repairValue;
    private String details;
    @ManyToOne
    @JoinColumn(name = "id_hardware_type",referencedColumnName = "id")
    private HardwareTypeModel hardwareType;

    public void update(BudgetModel budgetModel) {
        this.repairValue = budgetModel.getRepairValue();
        this.details = budgetModel.getDetails();
        this.hardwareType = budgetModel.getHardwareType();
    }

    public static List<BudgetModel> toBudgetModel(List<BudgetDto> listBudgetModel) {
        List<BudgetModel> listModel = new ArrayList<>();
        listBudgetModel.forEach(budgetModel -> listModel.add(new BudgetModel(budgetModel.getId(),
                budgetModel.getRepairValue(), budgetModel.getDetails(),
                HardwareTypeModel.toHardwareTypeModel(budgetModel.getHardwareType()))));
        return listModel;
    }
}
