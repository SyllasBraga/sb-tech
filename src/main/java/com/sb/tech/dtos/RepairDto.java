package com.sb.tech.dtos;

import com.sb.tech.models.RepairModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RepairDto {
    private Long id;
    private Timestamp entryDate;
    private Timestamp repairTimeStipulated;
    private Timestamp outDate;
    private ClientDto client;
    private TechnicianDto technician;
    private String paymentStatus;
    private List<BudgetDto> listBudget;

    public static RepairDto toRepairDto(RepairModel repairModel) {
        return new RepairDto(repairModel.getId(), repairModel.getEntryDate(), repairModel.getRepairTimeStipulated(),
                repairModel.getOutDate(), ClientDto.toClientDto(repairModel.getIdClient()),
                TechnicianDto.toTechnicianDto(repairModel.getIdTechnician()),
                repairModel.getPaymentStatus(), BudgetDto.toBudgetDto(repairModel.getBudgetList()));
    }
}