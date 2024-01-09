package com.sb.tech.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientModel {
    private String id;
    private String document;
    private String name;
    private String phone;
    private String email;
}
