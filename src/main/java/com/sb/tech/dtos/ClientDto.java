package com.sb.tech.dtos;

import com.sb.tech.models.ClientModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClientDto{
    private UUID id;
    private String document;
    private String name;
    private String phone;
    private String email;

    public static ClientDto toClientDto(ClientModel clientModel) {
        return new ClientDto(clientModel.getId(), clientModel.getDocument(), clientModel.getEmail(),
                clientModel.getEmail(), clientModel.getName());
    }
}
