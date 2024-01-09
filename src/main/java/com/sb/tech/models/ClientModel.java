package com.sb.tech.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientModel {
    private UUID id;
    private String document;
    private String name;
    private String phone;
    private String email;
}
