package com.sb.tech.dtos;

import com.sb.tech.models.ClientModel;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClientDto{
    private UUID id;
    @CPF(message = "Invalid document.")
    private String document;
    @NotBlank(message = "Field 'name' cannot be blank.")
    @Size(min = 3, max = 255)
    private String name;
    @NotBlank(message = "Field 'phone' cannot be blank.")
    private String phone;
    @Email(message = "Invalid email.")
    @NotBlank(message = "Field 'email' cannot be blank.")
    private String email;

    public static ClientDto toClientDto(ClientModel clientModel) {
        return new ClientDto(clientModel.getId(), clientModel.getDocument(), clientModel.getName(),
                clientModel.getPhone(), clientModel.getEmail());
    }
}
