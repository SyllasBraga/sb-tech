package com.sb.tech.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "budget")
public class BudgetModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private BigDecimal repairValue;
    private String details;
    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private HardwareTypeModel hardwareType;

    public void update(BudgetModel budgetModel) {
        this.repairValue = budgetModel.getRepairValue();
        this.details = budgetModel.getDetails();
        this.hardwareType = budgetModel.getHardwareType();
    }
}
