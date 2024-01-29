package com.sb.tech.models;

import com.sb.tech.dtos.RepairDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "repair")
public class RepairModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Timestamp entryDate;
    private Timestamp repairTimeStipulated;
    private Timestamp outDate;
    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private ClientModel idClient;
    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private TechnicianModel idTechnician;
    private String paymentStatus;
    @ManyToMany
    @JoinTable(name = "repair_budget",
            joinColumns = @JoinColumn(name = "id_repair"),
            inverseJoinColumns = @JoinColumn(name = "id_budget"))
    private List<BudgetModel> budgetList;

    public void update(RepairModel repairModel){
        this.id = repairModel.getId();
        this.entryDate = repairModel.getEntryDate();
        this.repairTimeStipulated = repairModel.getRepairTimeStipulated();
        this.outDate = repairModel.getOutDate();
        this.idClient = repairModel.getIdClient();
        this.idTechnician = repairModel.getIdTechnician();
        this.paymentStatus = repairModel.getPaymentStatus();
        this.budgetList = repairModel.getBudgetList();
    }

    public static RepairModel toRepairModel(RepairDto repairDto){
        return new RepairModel(repairDto.getId(), repairDto.getEntryDate(), repairDto.getRepairTimeStipulated(),
                repairDto.getOutDate(), ClientModel.toClientModel(repairDto.getClient()),
                TechnicianModel.toTechnicianModel(repairDto.getTechnician()), repairDto.getPaymentStatus(),
                BudgetModel.toBudgetModel(repairDto.getListBudget()));
    }
}
