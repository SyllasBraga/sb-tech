package com.sb.tech.dtos;

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
}
